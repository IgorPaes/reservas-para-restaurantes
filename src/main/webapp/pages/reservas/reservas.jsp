<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt=br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>R&R Reservas</title>

    <link rel="stylesheet" href="/pages/reservas/reservas.css">
    <link rel="stylesheet" href="/geral/geral.css">

    <script src="/pages/reservas/script_reserva.js" defer></script>
</head>
<body>
    <div class="back">
        <header>
            <figure class="logo">
                <img src="../../assets/logo.png" alt="Logo">
            </figure>
        </header>
        <main class="form_container">
            <form class="form_reserve" id="userFormReserve" action="" method="post">
                <div class="form_header">
                    <h1>FAÇA SUA RESERVA</h1>
                </div>
                <div class="section">
                    <h2 class="title">INFORMAÇÕES DO RESPONSÁVEL</h2>
                    <div class="line">
                    </div>
                    <div class="form_inputs">
                        <div class="grupo_input">
                            <label for="nome">Nome</label>
                            <input type="text" name="nome" id="input_nome" placeholder="Digite o seu nome" value="<%= request.getAttribute("NOME") %>" oninput="nameValidate()">

                        </div>
                        <div class="grupo_input">
                            <label for="telefone">Telefone</label>
                            <input type="tel" name="telefone" id="input_telefone" placeholder="Digite o seu telefone" oninput="telefoneValidate()" maxlength="14">      
                        </div>
                        <div class="grupo_input">
                            <label for="email">E-mail</label>
                            <input type="email" name="email" id="input_email" placeholder="Digite o seu e-mail" oninput="emailValidate()">
                        </div>
                        <div class="grupo_input">
                            <label for="cpf">CPF</label>
                            <input type="text" name="cpf" id="input_cpf" placeholder="Digite o seu cpf" oninput="cpfValidate()" maxlength="14">
                        </div>
                    </div>
                </div>

                <div class="title_container">
                    <h2 class="title">INFORMAÇÕES DA RESERVA</h2>
                    <div class="line"></div>
                </div>    

                <div class="section_info_reserva">
                    
                    <div class="input_group">
                        <label for="datas">Datas</label>
                        <input type="date" id="datas" name="data">
                    </div>
                    <div class="input_group">
                        <label for="horarios">Horários</label>
                        <input type="time" id="horarios" name="horario">
                    </div>
                    <div class="input_group">
                        <label for="n_pessoas">Qtd de Pessoas</label>
                        <select name="n_pessoas">
                            <option></option>
                            <option>1</option>
                            <option>2</option>
                            <option>3</option>
                            <option>4</option>
                            <option>5</option>
                            <option>6</option>
                            <option>7</option>
                            <option>8</option>
                            <option>9</option>
                            <option>10</option>
                        </select>
                    </div>

                    <div class="input_group">
                        <label for="comentario">Comentário</label>
                        <textarea name="comentario" id="comentario" placeholder="Comentário"></textarea>
                    </div>
                    
                </div>

                <div class="buttons">
                    <button type="reset">CANCELAR</button>
                    <button type="submit">CONFIRMAR RESERVA</button>
                </div>
            </form>


        </main>
    </div>

</body>

</html>
