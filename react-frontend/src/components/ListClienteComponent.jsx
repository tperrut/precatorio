import React, { Component } from 'react'
import ClienteService from '../services/ClienteService'

class ListClienteComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
                clientes: [],
                search_key :''
        }

        this.addCliente = this.addCliente.bind(this);
        this.editCliente = this.editCliente.bind(this);
        this.deleteCliente = this.deleteCliente.bind(this);
        this.findClientByNome = this.findClientByNome.bind(this);
        this.refresh = this.refresh.bind(this);

    }

    deleteCliente(id){
        ClienteService.deleteCliente(id).then( res => {
            this.setState({clientes: this.state.clientes.filter(Cliente => Cliente.id !== id)});
        });
        //alert("Cliente Deletado com sucesso!")
    }

    geraContrato(id){
        ClienteService.geraContrato(id).then( res => {
            this.setState({clientes: this.state.clientes});
        });
        this.props.history.push(`/clientes`);
    }

    viewCliente(id){
        this.props.history.push(`/view-cliente/${id}`);
    }

    editCliente(id){
        this.props.history.push(`/add-cliente/${id}`);
    }

    refresh(){
        ClienteService.getClientes().then((res) => {
            this.setState(state => ({clientes: res.data, search_key :''}));
        });
    }

    addCliente(){
        this.props.history.push('/add-cliente/_add');
    }

    findClientByNome = (event) => {
       event.preventDefault();

       let find = this.state.search_key;
       let filterd = this.state.clientes.filter( obj => obj.contato.nome.match(find));
      
       this.setState({clientes: filterd});

    }

    changeFilterField = (event) => {
        this.setState({search_key: event.target.value});
    }

    componentDidMount(){
        ClienteService.getClientes().then((res) => {
            this.setState(state => ({clientes: res.data, search_key :''}));
        });
    } 

    render() {
        return (
            <div>
                <h2 className="text-center">Lista de Clientes </h2>
                <br/>

                <div >
                    <div className="row" >
                            <br/>
                            
                            <div className="col-md-2">
                               
                                <button className="btn btn-secondary bi bi-plus" onClick={this.addCliente}> + Cliente</button>
                            </div>
                    
                            <div className="col-md-6">
                                <input type='text' placeholder="Digite o nome" name="search_key" className="form-control" 
                                    onChange={this.changeFilterField} value={this.state.search_key} />
                            </div>
                            
                            <button className="col-md-1 btn btn-primary" onClick={this.findClientByNome}>Filtrar</button>
                          
                        
                        <div className="col-md-3">
                                <button onClick={this.refresh} className="btn btn-success">Refresh </button>
                        </div>   
                    </div>
                    
                </div> 
                <br/> 
                 <div className = "row">
                        <table className = "table table-striped table-bordered">

                            <thead>
                                <tr>
                                    <th className="col-md-4"> Nome</th>
                                    <th className="col-md-2"> RG</th>
                                    <th className="col-md-2"> CPF</th>
                                    <th className="col-md-4"> Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    this.state.clientes.map(
                                        cliente => 
                                        <tr key = {cliente.id}>
                                             <td> {cliente.nomeContato} </td>   
                                             <td> {cliente.rg}</td>
                                             <td> {cliente.cpf}</td>
                                             <td>
                                                 <button onClick={ () => this.editCliente(cliente.id)} className="btn btn-info">Update </button>
                                                 <button style={{marginLeft: "10px"}} onClick={ () => this.deleteCliente(cliente.id)} className="btn btn-danger">Delete </button>
                                                 <button style={{marginLeft: "10px"}} onClick={ () => this.viewCliente(cliente.id)} className="btn btn-warning">View </button>
                                                 <button style={{marginLeft: "10px"}} onClick={ () => this.geraContrato(cliente.id)} className="btn btn-success">Gerar Contrato </button>

                                             </td>
                                        </tr>
                                    )
                                }
                            </tbody>
                        </table>

                 </div>

            </div>
        )
    }
}

export default ListClienteComponent
