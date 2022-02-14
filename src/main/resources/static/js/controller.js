async function crearCita() {
    let datos = {};
    console.log('Hola jeje')
    datos.descripcion = document.querySelector('#descripcion').value;
    datos.fecha = document.querySelector('#fecha').value;
    const fechaActual = new Date();
    const fechaComparar = new Date(datos.fecha);
    console.log(fechaComparar.toLocaleDateString() + ' y ' + fechaActual.toLocaleDateString())
    if (datos.fecha == "" || fechaComparar < fechaActual) {
        document.querySelector('#citaInfo').outerHTML = 'Fallo fecha';
    } else {
        const request = await fetch('http://localhost:8080/citas', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(datos)
        }).then()
        document.querySelector('#citaInfo').outerHTML = 'Cita establecida';
    }
}

async function verCita() {
    // let uuid = document.querySelector('#busUsu').value;
    const request = await fetch('http://localhost:8080/citas/1', {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    });
    const citaInfo = await request.json();

    let cita = '';
    cita += ' - Descripcion: ' + citaInfo.descripcion + '<br>' +
        'Fecha: ' + citaInfo.fecha + '<br>';

    document.querySelector('#verCita').outerHTML = cita;
}

function validateForm() {
    let x = document.forms["myForm"]["fname"].value;
    if (x == "") {
        alert("Name must be filled out");
        return false;
    }
}