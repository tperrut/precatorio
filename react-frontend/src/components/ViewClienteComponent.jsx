import React, { Component } from 'react'
import ClienteService from '../services/ClienteService'

class ViewClienteComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            cliente: {}
        }
    }

    componentDidMount(){
        ClienteService.getClienteById(this.state.id).then( res => {
            this.setState({cliente: res.data});
        })
    }
    
    cancel(){
        this.props.history.push('/clientes');
    }

    render() {
        return (
            <div>
                <br></br>
                <div className = "card col-md-6 offset-md-3">
                    <h3 className = "text-center"> View Cliente Details</h3>
                    <div className = "card-body">
                        <div className = "row">
                            <label> Nome: </label>
                            <div> { this.state.cliente.nome }</div>
                        </div>
                        <div className = "row">
                            <label> Telefone: </label>
                            <div> { this.state.cliente.telefone }</div>
                        </div>
                        <div className = "row">
                            <label> Email: </label>
                            <div> { this.state.cliente.email }</div>
                        </div>
                    </div>

                    <div className = "row">
                        <button style={{marginLeft: "10px"}} className="btn btn-secondary" onClick={this.cancel.bind(this)} >Voltar</button>
                    </div>
                </div>
            </div>
        )
    }
}

export default ViewClienteComponent
