import { useState } from "react";

import React, { Component } from 'react'
import ClienteService from '../services/ClienteService';

 

class CreateClienteComponent extends Component {
   
    

    constructor(props) {
        super(props)

        this.state = {
            // step 2
            id: this.props.match.params.id,
            showConjugue: false,
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
            nomeConjugue:'',
            profissao: '',
            numProcesso:'',
            origemTramitacao:'',
            numPrecatorio:'',
            nomeConjugue:          '',
            rgConjugue:            '',
            cpfConjugue:           '',
            nacionalidadeConjugue: '',
            nomeConjugue:          '',
            profissaoConjugue:     '',
            cidadeConjugue:        '',
            bairroConjugue:        '',
            bairro:             '',
            estadoConjugue:        '',
            paisConjugue:          '',
            numeroConjugue:        '',
            complementoConjugue:   '',
            cepConjugue:           '',
            logradouroConjugue:    '',
            codBanco:              '',
            nomeBanco:             '',
            agencia:              '',
            contaCorrente:             '',
            enteDevedor:           '',

            cliente: {}

        }

        this.changeNomeHandler = this.changeNomeHandler.bind(this);
        this.changeNomeConjugueHandler = this.changeNomeConjugueHandler.bind(this);

        this.changeRgHandler = this.changeRgHandler.bind(this);
        this.changeRgConjugueHandler = this.changeRgConjugueHandler.bind(this);

        this.changeCpfHandler = this.changeCpfHandler.bind(this);
        this.changeCpfConjugueHandler = this.changeCpfConjugueHandler.bind(this);

        this.changeNacionalidadeHandler = this.changeNacionalidadeHandler.bind(this);
        this.changeNacionalidadeConjugueHandler = this.changeNacionalidadeConjugueHandler.bind(this);


        this.changeTelefoneHandler = this.changeTelefoneHandler.bind(this);

        this.saveOrUpdateclientes = this.saveOrUpdateclientes.bind(this);

        this.changeEmailHandler = this.changeEmailHandler.bind(this)
        this.changeEstadoCivilHandler = this.changeEstadoCivilHandler.bind(this)

        this.getListEstados = this.getListEstados.bind(this);

        this.changeProfissaoConjugueHandler = this.changeProfissaoConjugueHandler.bind(this)
        this.changeProfissaoHandler = this.changeProfissaoHandler.bind(this)

        this.changeCepHandler = this.changeCepHandler.bind(this)
        this.changeCepConjugueHandler = this.changeCepConjugueHandler.bind(this)


    }


    // step 3
    componentDidMount(){
        //const [emailError, setEmailError] = useState('');
        //const [nomeError, setNomeError] = useState('');  


        // step 4
        if(this.state.id === '_add'){
            return
        }else{
            

            ClienteService.getClienteById(this.state.id).then( (res) =>{
                let cliente = res.data;


                var s = cliente.estadoCivil ;
                var isMarried = false;
                if (s.match(/CASADO.*/)) {
                  isMarried = true;
                }

                this.setState({
                  //  id:              cliente.id,

                    showConjugue: isMarried,

                    nomeConjugue:          cliente.nomeConjugue,
                    rgConjugue:            cliente.rgConjugue,
                    cpfConjugue:           cliente.cpfConjugue,
                    nacionalidadeConjugue: cliente.nacionalidadeConjugue,
                    nomeConjugue:          cliente.nomeConjugue,
                    profissaoConjugue:     cliente.profissaoConjugue,

                    logradouroConjugue:    cliente.logradouroConjugue,
                    cidadeConjugue:        cliente.cidadeConjugue,
                    estadoConjugue:        cliente.estadoConjugue,
                    paisConjugue:          cliente.paisConjugue,
                    numeroConjugue:        cliente.numeroConjugue,
                    complementoConjugue:   cliente.complementoConjugue,
                    cepConjugue:           cliente.cepConjugue,

                    bairroConjugue:        cliente.bairroConjugue,
                    bairro:                cliente.bairro,
                    nomeContato:     cliente.nomeContato, 
                    valorContrato:   cliente.valorContrato,
                    telefone:        cliente.telefone, 
                    email:           cliente.email,
                    rg:              cliente.rg,
                    cpf:             cliente.cpf,
                    estadoCivil:     cliente.estadoCivil,
                    nacionalidade:   cliente.nacionalidade,
                    logradouro:      cliente.logradouro,
                    cidade:          cliente.cidade,
                    estado:          cliente.estado,
                    pais:            cliente.pais,
                    numero:          cliente.numero,
                    complemento:     cliente.complemento,
                    cep:             cliente.cep,
                    percentual:      cliente.percentual,
                    cliente:         cliente,
                    nomeConjugue:    cliente.nomeConjugue,
                    profissao:       cliente.profissao,
                    numProcesso:     cliente.numProcesso,
                    origemTramitacao:cliente.origemTramitacao,
                    numPrecatorio:   cliente.numPrecatorio,
                    codBanco:        cliente.codBanco,
                    nomeBanco:       cliente.nomeBanco,
                    contaCorrente:   cliente.contaCorrente,
                    agencia:         cliente.agencia,
                    enteDevedor:     cliente.enteDevedor

                })    
            });
        }        
    }

    saveOrUpdateclientes = (e) => {
        e.preventDefault();
        let cliente = {
            nomeContato:   this.state.nomeContato,
            rg:            this.state.rg,
            cpf:           this.state.cpf,
            nacionalidade: this.state.nacionalidade,
            profissao:       this.state.profissao,

            nomeConjugue:    this.state.nomeConjugue,
            rgConjugue:            this.state.rgConjugue,
            cpfConjugue:           this.state.cpfConjugue,
            nacionalidadeConjugue: this.state.nacionalidadeConjugue,
            nomeConjugue:    this.state.nomeConjugue,
            profissaoConjugue:       this.state.profissaoConjugue,

            logradouroConjugue:    this.state.logradouroConjugue,
            cidadeConjugue:        this.state.cidadeConjugue,
            estadoConjugue:        this.state.estadoConjugue,
            paisConjugue:          this.state.paisConjugue,
            numeroConjugue:        this.state.numeroConjugue,
            complementoConjugue:   this.state.complementoConjugue,
            cepConjugue:           this.state.cepConjugue,

            logradouro:    this.state.logradouro,
            cidade:        this.state.cidade,
            estado:        this.state.estado,
            pais:          this.state.pais,
            numero:        this.state.numero,
            complemento:   this.state.complemento,
            cep:           this.state.cep,
            bairroConjugue:        this.state.bairroConjugue,
            bairro:             this.state.bairro,

            valorContrato:   this.state.valorContrato,
            percentual:      this.state.percentual,
            numProcesso:     this.state.numProcesso,
            origemTramitacao:this.state.origemTramitacao,

            numPrecatorio:   this.state.numPrecatorio,
            telefone:        this.state.telefone,
            email:           this.state.email,
            estadoCivil:     this.state.estadoCivil,
            codBanco:        this.state.codBanco,
            nomeBanco:       this.state.nomeBanco,
            agencia:         this.state.agencia,
            contaCorrente:   this.state.contaCorrente,
            enteDevedor:     this.state.enteDevedor

        };

        //console.log('cliente => ' + JSON.stringify(cliente));

        // step 5
        if(this.state.id === '_add'){
            ClienteService.createCliente(cliente).then(res =>{


                if(res.data.fieldErrors) {
                    alert("Erro ao salvar o Cliente, contate o Thiagão do Gongolo!!!")

                    res.data.fieldErrors.forEach(fieldError => {
                      if(fieldError.field == 'email'){
                        //setEmailError(fieldError.message);
                      }
          
                      if(fieldError.field === 'nomeContato'){
                        //setNomeError(fieldError.message);
                      }
                    });
                  } else {
                    this.props.history.push('/clientes');
                    alert("Cliente Criado com Sucesso!")
                  }
                }).catch((err) => err);

        }else{

            ClienteService.updateCliente(cliente, this.state.id).then( res => {

                if(res.data.fieldErrors) {

                    alert("Erro ao salvar o Cliente, contate o Thiagão do Gongolo!!!")

                    res.data.fieldErrors.forEach(fieldError => {
                      if(fieldError.field === 'email'){
                        //setEmailError(fieldError.message);
                      }
          
                      if(fieldError.field === 'nomeContato'){
                        //setNomeError(fieldError.message);
                      }
                    });
                  } else {
                    this.props.history.push('/clientes');
                    alert("Cliente Editado com Sucesso!")
                  }
                }).catch((err) => err);
                
        }
    }
    

    changeEstadoCivilHandler = (event) => {
        if(event.target.value === "CASADO"){
            this.setState({estadoCivil: event.target.value, showConjugue: true});
        }else{
            this.setState({estadoCivil: event.target.value, showConjugue: false});
        }

    }

    changeTelefoneHandler = (event) => {
        this.setState({telefone: event.target.value});
    }

    changeEmailHandler = (event) => {
        this.setState({email: event.target.value});
    }

    changeNomeHandler= (event) => {
        this.setState({nomeContato: event.target.value});
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


    changeLogradouroHandler = (event) => {
        this.setState({logradouro:  event.target.value});
    }

    changeCidadeHandler = (event) => {
        this.setState({cidade: event.target.value});
    }

    changeCepHandler = (event) => {
        this.setState({cep: event.target.value});
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

    changeProfissaoHandler = (event) => {
        this.setState({profissao: event.target.value});
    }



    changeNomeConjugueHandler = (event) => {
        this.setState({nomeConjugue: event.target.value});
    }

    changeRgConjugueHandler= (event) => {
        this.setState({rgConjugue: event.target.value});
    }

    changeCpfConjugueHandler= (event) => {
        this.setState({cpfConjugue: event.target.value});
    }

    changeNacionalidadeConjugueHandler = (event) => {
        this.setState({nacionalidadeConjugue: event.target.value});
    }


    changeLogradouroConjugueHandler = (event) => {
        this.setState({logradouroConjugue:  event.target.value});
    }

    changeCidadeConjugueHandler = (event) => {
        this.setState({cidadeConjugue: event.target.value});
    }

    changeCepConjugueHandler = (event) => {
        this.setState({cepConjugue: event.target.value});
    }

    changeEstadoConjugueHandler = (event) => {
        this.setState({estadoConjugue: event.target.value});
    }

    changePaisConjugueHandler = (event) => {
        this.setState({paisConjugue: event.target.value});
    }

    changeComplementoConjugueHandler = (event) => {
        this.setState({complementoConjugue: event.target.value});
    }

    changeNumeroConjugueHandler = (event) => {
        this.setState({numeroConjugue: event.target.value});
    }


    changeNumProcessoHandler = (event) => {
        this.setState({numProcesso: event.target.value});
    }
    changeOrigemTramitacaoHandler = (event) => {
        this.setState({origemTramitacao: event.target.value});
    }

    changeNumPrecatorioHandler = (event) => {
        this.setState({numPrecatorio: event.target.value});
    }

    changeProfissaoConjugueHandler = (event) => {
        this.setState({profissaoConjugue: event.target.value});
    }

    changeCodBancolHandler = (event) => {
            this.setState({codBanco: event.target.value});
    }

    changeNomeBancoHandler = (event) => {
            this.setState({nomeBanco: event.target.value});
    }

    changeContaCorrentelHandler = (event) => {
                this.setState({contaCorrente: event.target.value});
    }

    changeAgenciaHandler = (event) => {
            this.setState({agencia: event.target.value});
    }


    changeEnteDevedorHandler = (event) => {
        this.setState({enteDevedor: event.target.value});
    }

    changeBairroHandler = (event) => {
            this.setState({bairro: event.target.value});
    }

    changeBairroConjugueHandler = (event) => {
            this.setState({bairroConjugue: event.target.value});
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
            <div >
                <br></br>
                   <div className = "container" style={{background: "floralwhite",  borderRadius: "50px"}}>
                      
                                {
                                    this.getTitle()
                                }
                                <br/>

                                <div >
                                    <form className = "row g-3 ">
                                        <span className = "row g-3 " style={{  borderRadius: "50px",   padding: "20px", margin: "auto", background: "gray"}}>

                                            <h4>Dados do Cliente</h4>
                                            <hr/>
                                            <div className = "col-6">
                                                <label> Nome: </label>
                                                <input placeholder="Nome Cliente" name="nome" className="form-control" 
                                                    value={this.state.nomeContato} onChange={this.changeNomeHandler}/>
                                                     {/*nomeError ? <span style={{ color: 'red', fontSize: '12px'}}>{nomeError}</span> : ''*/}
                                            </div>
                                            <div className = "col-md-6">
                                                 <label>Email: </label>

                                                    <input placeholder="Email " name="nome" className="form-control" 
                                                        value={this.state.email} onChange={this.changeEmailHandler}/>
                                                        {/*emailError ? <span style={{ color: 'red', fontSize: '12px'}}>{emailError}</span> : ''*/}
                                            </div>

                                            <div className = "col-md-4">
                                                 <label>Telefone: </label>

                                                    <input placeholder="Tel " name="telefone" className="form-control" 
                                                        value={this.state.telefone} onChange={this.changeTelefoneHandler}/>
                                            </div>

                                            <div className = "col-md-4">
                                                <label> RG: </label>
                                                <input placeholder="RG" name="rg" className="form-control" 
                                                    value={this.state.rg} onChange={this.changeRgHandler}/>
                                            </div>
                                            <div className = "col-md-4">
                                                <label> CPF : </label>
                                                <input placeholder="CPF" name="cpf" className="form-control" 
                                                    value={this.state.cpf} onChange={this.changeCpfHandler}/>
                                            </div>

                                             <div className = "col-md-4">
                                                 <label>Profissão: </label>

                                                    <input placeholder="Torneiro mecânico" name="profissao" className="form-control"
                                                        value={this.state.profissao} onChange={this.changeProfissaoHandler}/>
                                            </div>

                                            <div className = "col-md-4">
                                                <label> Nacionalidade: </label>
                                                <input placeholder="Brasileiro..." name="nacionalidade" className="form-control" 
                                                    value={this.state.nacionalidade} onChange={this.changeNacionalidadeHandler}/>
                                            </div>
                                            <div className = "col-md-4">
                                                <label> Estado Cívil: </label>
                                                <select name="estadoCivil" className="form-control"  class="form-select" value={this.state.estadoCivil} onChange={this.changeEstadoCivilHandler} >
                                                    <option selected>Selecione ...</option>
                                                    <option value={"CASADO"} >Casado</option>
                                                    <option value={ "SOLTEIRO"} >Solteiro</option>
                                                </select>    
                                            </div>
                                             <h4>Dados Bancários </h4>
                                             <hr/>
                                             <div className = "col-3">
                                                <label> Nome Banco: </label>
                                                <input placeholder="Nome Banco" name="nomeBanco" className="form-control"
                                                    value={this.state.nomeBanco} onChange={this.changeNomeBancoHandler}/>
                                                     {/*nomeError ? <span style={{ color: 'red', fontSize: '12px'}}>{nomeError}</span> : ''*/}
                                            </div>
                                            <div className = "col-md-3">
                                                 <label>Cod. Banco: </label>

                                                    <input placeholder="Código do Banco" name="codBanco" className="form-control"
                                                        value={this.state.codBanco} onChange={this.changeCodBancolHandler}/>
                                                        {/*emailError ? <span style={{ color: 'red', fontSize: '12px'}}>{emailError}</span> : ''*/}
                                            </div>
                                            <div className = "col-3">
                                                <label> Agência: </label>
                                                <input placeholder="Cod. Agência" name="agencia" className="form-control"
                                                    value={this.state.agencia} onChange={this.changeAgenciaHandler}/>
                                                     {/*nomeError ? <span style={{ color: 'red', fontSize: '12px'}}>{nomeError}</span> : ''*/}
                                            </div>
                                            <div className = "col-md-3">
                                                 <label>Conta Corrente: </label>

                                                    <input placeholder="Conta Corrente" name="contaCorrente" className="form-control"
                                                        value={this.state.contaCorrente} onChange={this.changeContaCorrentelHandler}/>
                                                        {/*emailError ? <span style={{ color: 'red', fontSize: '12px'}}>{emailError}</span> : ''*/}
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
                                                <div className = "col-6">
                                                    <label> Complemento: </label>
                                                    <input placeholder="AP ..." name="complemento" className="form-control"
                                                        value={this.state.complemento} onChange={this.changeComplementoHandler}/>
                                                </div>

                                                <div className = "col-6">
                                                    <label> Bairro: </label>
                                                    <input placeholder="..." name="bairro" className="form-control"
                                                        value={this.state.bairro} onChange={this.changeBairroHandler}/>
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
                                        </span>
                                         {this.state.showConjugue && <span style={{  borderRadius: "50px", margin: "auto", paddingBottom: "20px", marginTop: "14px", background: "darkgrey"}} className = "row g-3 ">

                                              {this.state.showConjugue && <h4 style={{marginTop: "20px"}}>Dados do Conjugue</h4> }
                                              {this.state.showConjugue && <hr/> }

                                              {this.state.showConjugue &&   <div className = "col-8">
                                                    <label> Nome: </label>
                                                    <input placeholder="Nome Conjugue" name="nomeConjugue" className="form-control"
                                                        value={this.state.nomeConjugue} onChange={this.changeNomeConjugueHandler}/>
                                                         {/*nomeError ? <span style={{ color: 'red', fontSize: '12px'}}>{nomeError}</span> : ''*/}
                                                 </div>
                                              }

                                               {this.state.showConjugue &&  <div className = "col-md-4">
                                                   <label>Profissão: </label>

                                                      <input placeholder="Piloto" name="profissaoConjugue" className="form-control"
                                                          value={this.state.profissaoConjugue} onChange={this.changeProfissaoConjugueHandler}/>
                                              </div>
                                               }

                                              {this.state.showConjugue &&    <div className = "col-md-4">
                                                    <label> RG: </label>
                                                    <input placeholder="RG" name="rgConjugue" className="form-control"
                                                        value={this.state.rgConjugue} onChange={this.changeRgConjugueHandler}/>
                                                 </div>
                                              }

                                              {this.state.showConjugue &&    <div className = "col-md-4">
                                                    <label> CPF: </label>
                                                    <input placeholder="CPF" name="cpfConjugue" className="form-control"
                                                        value={this.state.cpfConjugue} onChange={this.changeCpfConjugueHandler}/>
                                                 </div>
                                             }

                                             {this.state.showConjugue &&    <div className = "col-md-4">
                                                    <label> Nacionalidade: </label>
                                                    <input placeholder="Brasileiro(a)..." name="nacionalidadeConjugue" className="form-control"
                                                        value={this.state.nacionalidadeConjugue} onChange={this.changeNacionalidadeConjugueHandler}/>
                                                 </div>
                                             }


                                             <h4>Endereço </h4>
                                                     <hr/>
                                                     <div className = "col-md-3">
                                                         <label> Cep: </label>
                                                         <input placeholder="Cep" name="cepConjugue" className="form-control"
                                                             value={this.state.cepConjugue} onChange={this.changeCepConjugueHandler}/>
                                                     </div>

                                                     <div className = "col-md-7">
                                                         <label> Logradouro: </label>
                                                         <input placeholder="Logradouro" name="logradouroConjugue" className="form-control"
                                                             value={this.state.logradouroConjugue} onChange={this.changeLogradouroConjugueHandler}/>
                                                     </div>



                                                     <div className = "col-md-2">
                                                         <label> Número: </label>
                                                         <input placeholder="Número" name="numeroConjugue" className="form-control"
                                                             value={this.state.numeroConjugue} onChange={this.changeNumeroConjugueHandler}/>
                                                     </div>
                                                     <div className = "col-6">
                                                         <label> Complemento: </label>
                                                         <input placeholder="AP ..." name="complemento" className="form-control"
                                                             value={this.state.complementoConjugue} onChange={this.changeComplementoConjugueHandler}/>
                                                     </div>

                                                      <div className = "col-6">
                                                         <label> Bairro: </label>
                                                         <input placeholder="..." name="bairroConjugue" className="form-control"
                                                             value={this.state.bairroConjugue} onChange={this.changeBairroConjugueHandler}/>
                                                     </div>
                                                     <div className = "col-md-4">
                                                         <label> País : </label>
                                                         <input placeholder="País" name="pais" className="form-control"
                                                             value={this.state.paisConjugue} onChange={this.changePaisConjugueHandler}/>
                                                     </div>



                                                     <div className = "col-md-4">
                                                     <label> Estado : </label>
                                                         <select name="estadoConjugue" className="form-control" value={this.state.estadoConjugue} onChange={this.changeEstadoConjugueHandler} >
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
                                                         <input placeholder="Cidade " name="cidadeConjugue" className="form-control"
                                                             value={this.state.cidadeConjugue} onChange={this.changeCidadeConjugueHandler}/>
                                                     </div>

                                             </span>
                                             }

                                          <span style={{marginLeft: "initial", borderRadius: "50px", paddingBottom: "20px", marginTop: "14px", background: "darkgrey"}} className = "row g-3 ">

                                            <h4 style={{marginTop: "14px"}} >Dados do Contrato</h4>
                                            <hr/>
                                            <div className = "col-md-3">
                                                <label> Valor do Contrato: </label>
                                                <input placeholder="Valor" name="valor" className="form-control" 
                                                    value={this.state.valorContrato} onChange={this.changeValorContratoHandler}/>
                                            </div>

                                            <div className = "col-md-2">
                                                <label> Percentual : </label>
                                                <input placeholder="% ..." name="percentual" className="form-control"
                                                    value={this.state.percentual} onChange={this.changePercentualHandler}/>
                                            </div>

                                            <div className = "col-md-7">
                                                <label> Origem Tramitação: </label>
                                                <input placeholder="" name="origemTramitacao" className="form-control"
                                                    value={this.state.origemTramitacao} onChange={this.changeOrigemTramitacaoHandler}/>
                                            </div>


                                             <div className = "col-md-4">
                                                <label> Número do Processo: </label>
                                                <input placeholder="" name="numProcesso" className="form-control"
                                                    value={this.state.numProcesso} onChange={this.changeNumProcessoHandler}/>
                                            </div>

                                             <div className = "col-md-4 ">
                                                <label> Ente Devedor : </label>
                                                <input placeholder="..." name="enteDevedor" className="form-control"
                                                    value={this.state.enteDevedor} onChange={this.changeEnteDevedorHandler}/>
                                            </div>

                                            <div className = "col-md-4">
                                                <label> Número do Precatório : </label>
                                                <input placeholder="..." name="numPrecatorio" className="form-control"
                                                    value={this.state.numPrecatorio} onChange={this.changeNumPrecatorioHandler}/>
                                            </div>

                                        </span>

                                            
                                          
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
