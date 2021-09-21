package com.mguzman.empledata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mguzman.empledata.model.ResetToken;
import com.mguzman.empledata.model.Usuario;
import com.mguzman.empledata.service.LoginService;
import com.mguzman.empledata.service.ResetTokenService;
import com.mguzman.empledata.util.EmailUtil;
import com.mguzman.empledata.util.Mail;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
    private LoginService service;

    @Autowired
    private ResetTokenService tokenService;

    @Autowired
    private EmailUtil emailUtil;

    @Autowired
    private BCryptPasswordEncoder bcrypt;

    @Value("${reintos.token}")
    private String count;

    @PostMapping(value = "/enviarCorreo", consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<Integer> enviarCorreo(@RequestBody String correo) {
        int rpta = 0;
        try {
            Usuario us = service.verificarNombreUsuario(correo);
            if (us != null && us.getIdUsuario() > 0) {

                ResetToken token = new ResetToken();
                token.setToken(UUID.randomUUID().toString());
                token.setUser(us);
                token.setExpiracion(10);
                tokenService.guardar(token);

                Mail mail = new Mail();
                mail.setFrom("email.prueba.demo@gmail.com");
                mail.setTo(us.getUserName());
                mail.setSubject("RESTABLECER CONTRASEÑA - MEDIAPP");

                Map<String, Object> model = new HashMap<>();
                String url = "http://localhost:4200/recuperar/" + token.getToken();
                model.put("user", us.getUserName());
                model.put("resetUrl", url);
                mail.setModel(model);
                emailUtil.enviarMail(mail);
                rpta = 1;
            }
        } catch(Exception e) {
            return new ResponseEntity<Integer>(rpta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Integer>(rpta, HttpStatus.OK);
    }


    @GetMapping(value = "/restablecer/verificar/{token}")
    public ResponseEntity<Integer> restablecerClave(@PathVariable("token") String token) {
        int rpta = 0;
        try {
            if (token != null && !token.isEmpty()) {
                ResetToken rt = tokenService.findByToken(token);
                if (rt != null && rt.getId() > 0) {
                    if (!rt.estaExpirado()) {
                        if(!rt.getCount().equals(count)){
                            rpta = 1;
                        }
                    }
                }
            }
        } catch (Exception e) {
            return new ResponseEntity<Integer>(rpta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Integer>(rpta, HttpStatus.OK);
    }


    @PostMapping(value = "/restablecer/{token}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> restablecerClave(@PathVariable("token") String token, @RequestBody String clave ) {
        int rpta = 0;
        try {
            ResetToken rt = tokenService.findByToken(token);
            String claveHash = bcrypt.encode(clave);
            rpta = service.cambiarClave(claveHash, rt.getUser().getUserName());
            tokenService.eliminar(rt);
        } catch (Exception e) {
            return new ResponseEntity<Integer>(rpta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Integer>(rpta, HttpStatus.OK);
    }

}
