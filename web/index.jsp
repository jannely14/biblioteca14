<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <title>Login</title>
    <style>
        body {
            background: linear-gradient(to right, rgba(135, 206, 235, 0.8), rgba(70, 130, 180, 0.8)), /* Celeste a azul */
                        url('img/fondo.jpg'); /* Cambia esta ruta si es necesario */
            background-size: cover;
            background-position: center;
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
        }
        .card {
            opacity: 0.9; /* Transparencia de la tarjeta */
        }
    </style>
</head>
<body>
    <div class="container mt-4 col-lg-4">
        <div class="card col-sm-10">
            <div class="card-body">
                <form class="form-sign" action="Validar" method="POST">
                    <div class="form-group text-center">
                        <h3>Bienvenidos al Sistema de Biblioteca</h3>
                        <img src="img/biblio3.jpg" alt="360" width="170"/>
                    </div>
                    <div class="form-group">
                        <label>Usuario:</label>
                        <input type="text" name="txtuser" value="emp01" class="form-control">
                    </div>
                    <div class="form-group">
                        <label>Password:</label>
                        <input type="password" name="txtpass" value="123" class="form-control">
                    </div>
                    <input type="submit" name="accion" value="Ingresar" class="btn btn-primary btn-block">
 
