import axios from 'axios';

const CLIENTE_API_BASE_URL = "http://localhost:8081/api/v1/cliente";
const CLIENTE_API_DOWNLOAD_URL = "http://localhost:8081/api/v1/cliente/download/";

class ClienteService {

    geraContrato(id){
        return axios.get(CLIENTE_API_DOWNLOAD_URL + id);
    }

    getClientes(){
        return axios.get(CLIENTE_API_BASE_URL);
    }

    createCliente(cliente){
        return axios.post(CLIENTE_API_BASE_URL, cliente);
    }

    getClienteById(clienteId){
        return axios.get(CLIENTE_API_BASE_URL + '/' + clienteId);
    }

    updateCliente(cliente, clienteId){
        return axios.put(CLIENTE_API_BASE_URL + '/' + clienteId, cliente);
    }

    deleteCliente(clienteId){
        return axios.delete(CLIENTE_API_BASE_URL + '/' + clienteId);
    }
}

export default new ClienteService()