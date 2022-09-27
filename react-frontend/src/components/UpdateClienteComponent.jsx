import React, { Component } from 'react'
import ClienteService from '../services/ClienteService';

class UpdateClienteComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            nome: '',
            rg: '',
            cpf: '',
            estado_civil: '',
            telefone:'',
            nacionalidade:'',
            email: '',
            search_key :'',
            logradouro: '',
            cidade:'',
            estado:'',
            pais:'',
            numero:'',
            complemento:'',
            valor_contrato:'',
            percentual:''
        }
        this.changeNomeHandler = this.changeNomeHandler.bind(this);
        this.changeRgHandler = this.changeRgHandler.bind(this);
        this.changeCpfHandler = this.changeCpfHandler.bind(this);
        this.changeNacionalidadeHandler = this.changeNacionalidadeHandler.bind(this);
        this.changeTelefoneHandler = this.changeTelefoneHandler.bind(this);

        this.saveOrUpdateclientes = this.saveOrUpdateclientes.bind(this);

        this.getListEstados = this.getListEstados.bind(this);
        this.updateCliente = this.updateCliente.bind(this);
    }

    componentDidMount(){

        ClienteService.getClienteById(this.state.id).then( (res) =>{
            let cliente = res.data;
            this.setState({
                id:              cliente.id,
                nomeContato:     cliente.nome, 
                telefone:        cliente.telefone, 
                email:           cliente.email,
                rg:              cliente.rg,
                cpf:             cliente.cpf,
                estado_civil:    cliente.estado_civil,
                telefone:        cliente.telefone,
                nacionalidade:   cliente.nacionalidade,
                logradouro:      cliente.logradouro,
                cidade:          cliente.cidade,
                estado:          cliente.estado,
                pais:            cliente.pais,
                numero:          cliente.numero,
                complemento:     cliente.complemento,
                cep:             cliente.cep,
                valor_contrato:  cliente.valor_contrato,
                percentual:      cliente.percentual
              
            });
        });
    }

    updateCliente = (e) => {
        e.preventDefault();
        let cliente = {firstName: this.state.firstName, lastName: this.state.lastName, emailId: this.state.emailId};
        console.log('cliente => ' + JSON.stringify(cliente));
        console.log('id => ' + JSON.stringify(this.state.id));
        ClienteService.updateCliente(cliente, this.state.id).then( res => {
            this.props.history.push('/clientes');
        });
    }
    
    changeNomeHandler= (event) => {
        this.setState({nome: event.target.value});
    }

    changeRgHandler= (event) => {
        this.setState({rg: event.target.value});
    }

    changeCpfHandler= (event) => {
        this.setState({cpf: event.target.value});
    }
    
    changeNacionalidadeHandler = (event) => {
        this.setState({nacionalidade: event.target.value});
    }

    changeTelefoneHandler = (event) => {
        this.setState({telefone: event.target.value});
    }

    changeLogradouroHandler = (event) => {
        this.setState({logradouro:  event.target.value});
    }

    changeTelefoneHandler = (event) => {
        this.setState({cidade: event.target.value});
    }

    changeEstadoHandler = (event) => {
        this.setState({estado: event.target.value});
    }

    changePaisHandler = (event) => {
        this.setState({pais: event.target.value});
    }

    changeComplementoHandler = (event) => {
        this.setState({complemento: event.target.value});
    }

    changeNumeroHandler = (event) => {
        this.setState({numero: event.target.value});
    }
    
    changePercentualHandler  = (event) => {
        this.setState({percentual: event.target.value});
    }

    changeValorContratoHandler = (event) => {
        this.setState({valor_contrato: event.target.value});
    }


    cancel(){
        this.props.history.push('/clientes');
    }

    render() {
        return (
            <div>
                <br></br>
                   <div className = "container">
                        <div className = "row">
                            <div className = "card col-md-6 offset-md-3 offset-md-3">
                                <h3 className="text-center">Update Cliente</h3>
                                <div className = "card-body">
                                    <form>
                                        <div className = "form-group">
                                            <label> First Name: </label>
                                            <input placeholder="First Name" name="firstName" className="form-control" 
                                                value={this.state.firstName} onChange={this.changeFirstNameHandler}/>
                                        </div>
                                        <div className = "form-group">
                                            <label> Last Name: </label>
                                            <input placeholder="Last Name" name="lastName" className="form-control" 
                                                value={this.state.lastName} onChange={this.changeLastNameHandler}/>
                                        </div>
                                        <div className = "form-group">
                                            <label> Email Id: </label>
                                            <input placeholder="Email Address" name="emailId" className="form-control" 
                                                value={this.state.emailId} onChange={this.changeEmailHandler}/>
                                        </div>

                                        <button className="btn btn-success" onClick={this.updateCliente}>Save</button>
                                        <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{marginLeft: "10px"}}>Cancel</button>
                                    </form>
                                </div>
                            </div>
                        </div>

                   </div>
            </div>
        )
    }
}

export default UpdateClienteComponent
