import axios from 'axios';

const CLIENTE_API_BASE_URL = "http://localhost:8081/api/v1/cliente";
const CLIENTE_API_DOWNLOAD_URL = "http://localhost:8081/api/v1/contrato/cliente/download/";

class ClienteService {

    geraContrato(id){
        return axios.get(CLIENTE_API_DOWNLOAD_URL + id);
    }

    getClientes(){
        return axios.get(CLIENTE_API_BASE_URL);
    }

    async createCliente(cliente) {
        let response = null
        try{
            response = await axios.post(CLIENTE_API_BASE_URL, cliente)
            console.log(response.data)
        }catch(error){
           response = error.response
           var arrayErro = error.response.data.fieldErrors
           arrayErro.forEach(showError);

           function showError(e){
              alert("Erro no Campo: " + e.field + "\nMensagem: " + e.message)
           }
          console.error(error);
        };
        return response

    }

    getClienteById(clienteId){
        return axios.get(CLIENTE_API_BASE_URL + '/' + clienteId);
    }

    async updateCliente(cliente, clienteId){
        let response = null

        try{
            response = await axios.put(CLIENTE_API_BASE_URL + '/' + clienteId, cliente)
            console.log(response.data)
        }catch(error){
            var arrayErro = error.response.data.fieldErrors
            arrayErro.forEach(showError);

            function showError(e){
               alert("Erro no Campo: " + e.field + "\nMensagem: " + e.message)
            }

            console.log(error.response.data.fieldErrors);
        };
        return response


    }


    deleteCliente(clienteId){
        return axios.delete(CLIENTE_API_BASE_URL + '/' + clienteId);
    }
}

export default new ClienteService()