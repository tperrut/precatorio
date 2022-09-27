import React, { Component } from 'react'
import ClienteService from '../services/ClienteService';

class CreateClienteComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            // step 2
            id: this.props.match.params.id,
            nomeContato: '',
            rg: '',
            cpf: '',
            estadoCivil: '',
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
            valorContrato:'',
            percentual:'',
            cliente: {}
        
        }
        this.changeNomeHandler = this.changeNomeHandler.bind(this);
        this.changeRgHandler = this.changeRgHandler.bind(this);
        this.changeCpfHandler = this.changeCpfHandler.bind(this);
        this.changeNacionalidadeHandler = this.changeNacionalidadeHandler.bind(this);
        this.changeTelefoneHandler = this.changeTelefoneHandler.bind(this);

        this.saveOrUpdateclientes = this.saveOrUpdateclientes.bind(this);
        this.changeEmailHandler = this.changeEmailHandler.bind(this)
        this.changeEstadoCivilHandler = this.changeEstadoCivilHandler.bind(this)
        this.getListEstados = this.getListEstados.bind(this);
    }

    // step 3
    componentDidMount(){

        // step 4
        if(this.state.id === '_add'){
            return
        }else{
            

            ClienteService.getClienteById(this.state.id).then( (res) =>{
                let cliente = res.data;
            
                this.setState({
                  //  id:              cliente.id,
                    nomeContato:     cliente.nomeContato, 
                    valorContrato:   cliente.valorContrato,
                    telefone:        cliente.telefone, 
                    email:           cliente.email,
                    rg:              cliente.rg,
                    cpf:             cliente.cpf,
                    estadoCivil:     cliente.estadoCivil,
                    telefone:        cliente.telefone,
                    nacionalidade:   cliente.nacionalidade,
                    logradouro:      cliente.logradouro,
                    cidade:          cliente.cidade,
                    estado:          cliente.estado,
                    pais:            cliente.pais,
                    numero:          cliente.numero,
                    complemento:     cliente.complemento,
                    cep:             cliente.cep,
                    valorContrato:  cliente.valorContrato,
                    percentual:      cliente.percentual,
                    cliente:         cliente

                })    
            });
        }        
    }

    saveOrUpdateclientes = (e) => {
        e.preventDefault();
        let cliente = {
            nomeContato:   this.state.nomeContato, 
            telefone:      this.state.telefone, 
            email:         this.state.email,
            rg:            this.state.rg,
            cpf:           this.state.cpf,
            estadoCivil:  this.state.estadoCivil,
            telefone:      this.state.telefone,
            nacionalidade: this.state.nacionalidade,
            logradouro:    this.state.logradouro,
            cidade:        this.state.cidade,
            estado:        this.state.estado,
            pais:          this.state.pais,
            numero:        this.state.numero,
            complemento:   this.state.complemento,
            cep:           this.state.cep,
            valorContrato:this.state.valorContrato,
            percentual:    this.state.percentual
        };

        console.log('cliente => ' + JSON.stringify(cliente));

        // step 5
        if(this.state.id === '_add'){
            ClienteService.createCliente(cliente).then(res =>{
                this.props.history.push('/clientes');
                alert("Cliente Criado com Sucesso!")

            });
        }else{
            ClienteService.updateCliente(cliente, this.state.id).then( res => {
                this.props.history.push('/clientes');
                alert("Cliente Editado com Sucesso!")
            });
        }
    }
    
    changeNomeHandler= (event) => {
        this.setState({nomeContato: event.target.value});
    }

    changeEstadoCivilHandler = (event) => {
        this.setState({estadoCivil: event.target.value});
    }

    changeEmailHandler = (event) => {
        this.setState({email: event.target.value});
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

    changeCidadeHandler = (event) => {
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
        this.setState({valorContrato: event.target.value});
    }

    changeCepHandler = (event) => {
        this.setState({cep: event.target.value});
    }

    cancel(){
        this.props.history.push('/clientes');
    }

    getTitle(){
        if(this.state.id === '_add'){
            return <h2 className="text-center">Cadastro de clientes</h2>
        }else{
            return <h2 className="text-center">Editar cliente</h2>
        }
    }

    getListEstados(){
        return [{"Sigla":"AC", "valor": "Acre"},
            {"Sigla":"AL", "valor": "Alagoas"},
            {"Sigla":"AP", "valor": "Amapá"},
            {"Sigla":"AM", "valor": "Amapá"},
            {"Sigla":"BA", "valor": "Bahia"},
            {"Sigla":"CE", "valor": "Ceará"},
            {"Sigla":"DF", "valor": "Distrito Federal"},
            {"Sigla":"ES", "valor": "Espirito Santo"},
            {"Sigla":"GO", "valor": "Goiás"},
            {"Sigla":"MA", "valor": "Maranhão"},
            {"Sigla":"MS", "valor": "Mato Grosso do Sul"},
            {"Sigla":"MT", "valor": "Mato Grosso"},
            {"Sigla":"MG", "valor": "Minas Gerais"},
            {"Sigla":"PA", "valor": "Pará"},
            {"Sigla":"PB", "valor": "Paraíba"},
            {"Sigla":"PR", "valor": "Paraná"},
            {"Sigla":"PE", "valor": "Pernambuco"},
            {"Sigla":"PI", "valor": "Piauí"},
            {"Sigla":"RJ", "valor": "Rio de Janeiro"},
            {"Sigla":"RS", "valor": "Rio Grande do Sul"},
            {"Sigla":"RO", "valor": "Rondônia"},
            {"Sigla":"RR", "valor": "Roraima"},
            {"Sigla":"SC", "valor": "Santa Catarina"},
            {"Sigla":"SP", "valor": "São Paulo"},
            {"Sigla":"SE", "valor": "Sergipe"},
            {"Sigla":"TO", "valor": "Tocantins"}]
    }

    render() {
        return (
            <div className = "firulinha">
                <br></br>
                   <div className = "container">
                      
                                {
                                    this.getTitle()
                                }
                                                                        <br/>

                                <div >
                                    <form className = "row g-3 ">

                                            <h4>Dados do Cliente</h4>
                                    <hr/>
                                            <div className = "col-12">
                                                <label> Nome: </label>
                                                <input placeholder="Nome Cliente" name="nome" className="form-control" 
                                                    value={this.state.nomeContato} onChange={this.changeNomeHandler}/>
                                            </div>
                                            <div className = "col-md-8">
                                                 <label>Email: </label>

                                                    <input placeholder="Email " name="nome" className="form-control" 
                                                        value={this.state.email} onChange={this.changeEmailHandler}/>
                                            </div>
                                            <div className = "col-md-4">
                                                 <label>Telefone: </label>

                                                    <input placeholder="Tel " name="telefone" className="form-control" 
                                                        value={this.state.telefone} onChange={this.changeTelefoneHandler}/>
                                            </div>

                                            <div className = "col-md-6">
                                                <label> RG: </label>
                                                <input placeholder="RG" name="rg" className="form-control" 
                                                    value={this.state.rg} onChange={this.changeRgHandler}/>
                                            </div>
                                            <div className = "col-md-6">
                                                <label> CPF : </label>
                                                <input placeholder="CPF" name="cpf" className="form-control" 
                                                    value={this.state.cpf} onChange={this.changeCpfHandler}/>
                                            </div>

                                            <div className = "col-md-6">
                                                <label> Nacionalidade: </label>
                                                <input placeholder="Brasileiro..." name="nacionalidade" className="form-control" 
                                                    value={this.state.nacionalidade} onChange={this.changeNacionalidadeHandler}/>
                                            </div>
                                            <div className = "col-md-6">
                                                <label> Estado Cívil: </label>
                                                <select name="estadoCivil" className="form-control"  class="form-select" value={this.state.estadoCivil} onChange={this.changeEstadoCivilHandler} >
                                                    <option selected>Selecione ...</option>
                                                    <option value={"CASADO"} >Casado</option>
                                                    <option value={ "SOLTEIRO"} >Solteiro</option>
                                                </select>    
                                            </div>

                                            <h4>Dados do Contrato</h4>
                                            <hr/>
                                            <div className = "col-md-3">
                                                <label> Valor do Contrato: </label>
                                                <input placeholder="Valor" name="valor" className="form-control" 
                                                    value={this.state.valorContrato} onChange={this.changeValorContratoHandler}/>
                                            </div>

                                            <div className = "col-md-7">
                                                <label> Percentual : </label>
                                                <input placeholder="% ..." name="percentual" className="form-control" 
                                                    value={this.state.percentual} onChange={this.changePercentualHandler}/>
                                            </div>


                                            <h4>Endereço </h4>

                                            <hr/>
                                            <div className = "col-md-3">
                                                <label> Cep: </label>
                                                <input placeholder="Cep" name="cep" className="form-control" 
                                                    value={this.state.cep} onChange={this.changeCepHandler}/>
                                            </div>

                                            <div className = "col-md-7">
                                                <label> Logradouro: </label>
                                                <input placeholder="Logradouro" name="logradouro" className="form-control" 
                                                    value={this.state.logradouro} onChange={this.changeLogradouroHandler}/>
                                            </div>

                                            
                                            
                                            <div className = "col-md-2">
                                                <label> Número: </label>
                                                <input placeholder="Número" name="numero" className="form-control" 
                                                    value={this.state.numero} onChange={this.changeNumeroHandler}/>
                                            </div>
                                            <div className = "col-12">
                                                <label> Complemento: </label>
                                                <input placeholder="AP ..." name="complemento" className="form-control" 
                                                    value={this.state.complemento} onChange={this.changeComplementoHandler}/>
                                            </div>
                                            <div className = "col-md-4">
                                                <label> País : </label>
                                                <input placeholder="País" name="pais" className="form-control" 
                                                    value={this.state.pais} onChange={this.changePaisHandler}/>
                                            </div>

                                            

                                            <div className = "col-md-4">
                                            <label> Estado : </label>
                                                <select name="estado" className="form-control" value={this.state.estado} onChange={this.changeEstadoHandler} >
                                                    <option selected>Selecione ...</option>
                                                    {
                                                         this.getListEstados().map(
                                                              estado => 
                                                              <option value={estado.valor} >{estado.Sigla}</option>
                                                        )      
                                                    }
                                                            
                                                </select>    

                                                
                                            </div>
                                            <div className = "col-md-4">
                                                <label>Cidade: </label>
                                                <input placeholder="Cidade " name="cidade" className="form-control" 
                                                    value={this.state.cidade} onChange={this.changeCidadeHandler}/>
                                            </div>
                                            
                                          
                                        <br/>
                                        <div className="col-2"></div>

                                        <button style={{marginBottom: "20px"}} className="btn btn-success col-3" onClick={this.saveOrUpdateclientes}>Save</button>
                                        <div className="col-2"></div>

                                        <button  style={{marginBottom: "20px"}} className="btn btn-danger col-3"  onClick={this.cancel.bind(this)} >Cancel</button>
                                        <div className="col-2"></div>
                                    </form>
                                </div>
                            </div>
                        </div>

          
        )
    }
}

export default CreateClienteComponent
