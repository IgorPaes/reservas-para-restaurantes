// function limitarAno(input) {
//     const inputValue = input.value;
//     if(inputValue.length > 10) {
//         input.value = inputValue.slice(0, 10);
//     }
// }

document.addEventListener('DOMContentLoaded', function() {
    const container = document.querySelector('.container_xl');
    const bgUrl = container.getAttribute('data-bg-url');
    container.style.setProperty('--bg-url', `url(${bgUrl})`);
});

let txtQtdPessoas = document.getElementById('qtdPessoas');
let QtdPessoas = parseInt(txtQtdPessoas.textContent);
function menosUm() {
    if(QtdPessoas > 1) {
        txtQtdPessoas.textContent = --QtdPessoas;
    }
}

function maisUm() {
    if(QtdPessoas < 15) {
        txtQtdPessoas.textContent = ++QtdPessoas;
    }
}

function limitarAno(input) {
    input.removeAttribute('min');
    input.removeAttribute('max');
    const today = new Date();
    const nextYear = new Date();
    nextYear.setFullYear(today.getFullYear() + 1);

    const formatDate = (date) => {
        const day = String(date.getDate()).padStart(2, '0');
        const month = String(date.getMonth() + 1).padStart(2, '0');
        const year = date.getFullYear();
        return `${year}-${month}-${day}`;
    };

    const minDate = formatDate(today);
    const maxDate = formatDate(nextYear);

    input.setAttribute('min', minDate);
    input.setAttribute('max', maxDate);
}

// Garante que os limites sejam configurados quando a página for carregada
document.addEventListener("DOMContentLoaded", function() {
    const dataInput = document.getElementById('data');
    limitarAno(dataInput);
});
// Função para criar um select de horários a partir de uma lista de horários específicos
function criarSelectHorarios(horariosEspecificos) {
    const horarioAtual = new Date(); // Obtém o horário atual
    const horaAtual = horarioAtual.getHours(); // Obtém a hora atual
    const horaAmanha = horaAtual + 24; // Obtém a hora 24 horas depois

    const selectHorarios = document.createElement('select');
    selectHorarios.name = 'horario';
    selectHorarios.id = 'hours';

    // Filtra os horários específicos para incluir apenas os horários entre o horário atual e 24 horas depois
    const horariosFiltrados = horariosEspecificos.filter(horario => {
        const horaHorario = parseInt(horario.split(':')[0]);
        return horaHorario >= horaAtual && horaHorario < horaAmanha;
    });

    for (const horario of horariosFiltrados) {
        const option = document.createElement('option');
        option.text = horario;
        selectHorarios.add(option);
    }

    // Selecionar a div correta usando o seletor de classe
    const bgInputDiv = document.querySelector('.input_block:nth-child(2) .bg_input');
    bgInputDiv.innerHTML = ''; // Limpar qualquer conteúdo existente
    bgInputDiv.appendChild(selectHorarios);
}

// Horários específicos que você deseja passar para a função
const horariosEspecificos = ['15:30', '17:00', '17:45', '18:25', '18:50', '19:45', '20:30', '21:30', '22:45', '23:45'];

// Chamada da função para criar o select de horários com os horários específicos a partir do horário atual até 24 horas depois
criarSelectHorarios(horariosEspecificos);

/* DESCOMENTE PARA TER O SELECT DE HORÁRIOS
// Função para pegar o horário atual em formato "HH:MM"
function getHorarioAtual() {
    const agora = new Date();
    const hora = agora.getHours().toString().padStart(2, '0');
    const minuto = agora.getMinutes().toString().padStart(2, '0');
    return `${hora}:${minuto}`;
}

// Função para criar um select de horários a partir do horário atual até o final do dia

function criarSelectHorarios() {
    const horarioAtual = getHorarioAtual();
    const selectHorarios = document.createElement('select');
    selectHorarios.name = 'horario';
    selectHorarios.id = 'hours';

    const horaAtual = parseInt(horarioAtual.split(':')[0]);
    const minutosAtual = parseInt(horarioAtual.split(':')[1]);

    for (let hora = horaAtual; hora <= 23; hora++) {
        for (let minuto = 0; minuto <= 45; minuto += 15) {
            if (hora === horaAtual && minuto < minutosAtual) {
                continue; // Pular horários anteriores ao atual
            }

            const option = document.createElement('option');
            const horaFormatada = hora.toString().padStart(2, '0');
            const minutoFormatado = minuto.toString().padStart(2, '0');
            option.text = `${horaFormatada}:${minutoFormatado}`;
            selectHorarios.add(option);
        }
    }

    // Selecionar a div correta usando o seletor de classe
    const bgInputDiv = document.querySelector('.input_block:nth-child(2) .bg_input');
    bgInputDiv.innerHTML = ''; // Limpar qualquer conteúdo existente
    bgInputDiv.appendChild(selectHorarios);
}

// Chamada da função para criar o select de horários
criarSelectHorarios();
*/


const notificacao = document.getElementById('notificacao');
const modalManager = {
    abrir: (data, horario, qtdPessoas) => {
        document.querySelector('.notification_modal #dt').textContent = data;
        document.querySelector('.notification_modal #hr').textContent = horario
        document.querySelector('.notification_modal #qp').textContent = qtdPessoas;
        notificacao.style.display = 'flex';
    },
    fechar: () => {
        notificacao.style.display = 'none';
    },
    confirmar: () => {
        const path = window.location.pathname;
        let idRestaurante = path.split('/').pop();
        fetch('/insert-reserve', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: 'idRestaurante=' + encodeURIComponent(idRestaurante) +
                'data=' + encodeURIComponent(document.getElementById("data").value) +
                '&horario=' + encodeURIComponent(document.getElementById("horario").value) +
                '&qtdPessoas=' + encodeURIComponent(document.getElementById("qtdPessoas").textContent) +
                '&comentario=' + encodeURIComponent(document.getElementById("comentario").value)
        }).then(response => {
            if (!response.ok) throw new Error('Erro ao inserir reserva');
            window.location.href = '/pages/gerenciamento/cliente/em-andamento/em-andamento.jsp';
        }).catch(error => {
            console.error('Erro:', error);
        });
    }
}

function confirmarReserva() {
    const data = document.getElementById("data").value;
    const horario = document.getElementById("horario").value;
    const qtdPessoas = document.getElementById("qtdPessoas").textContent;

    if(data.trim().length == "" && horario.trim().length == "" && qtdPessoas.trim().length == "") { 
        modalManager.abrir(data, horario, qtdPessoas);
    }else {
        alert("Complete os campos para continuar.")
    }

}
