INSERT INTO `usuario` (
    `id`,
    `nombre`,
    `nombreUsuario`,
    `email`,
    `password`,
    `token_password`
  )
VALUES
  (
    '1',
    'Johan',
    'ADMIN',
    'johanhernandez821@gmail.com',
    '$2a$10$Kh1wAmamgeEIJvIPZECRkOSlrdyIZRukiwWqLp1lBgog/5Ukvdzce'
  );

INSERT INTO `rol` (`id`, `rol_nombre`) VALUES ('1', 'ROLE_ADMIN');
INSERT INTO `rol` (`id`, `rol_nombre`) VALUES ('2', 'ROLE_USER');

INSERT INTO `usuario_rol` (`usuario_id`, `rol_id`) VALUES ('1', '1');