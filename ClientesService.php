<?php
    // Arquivo de controle : tipo de servicos oferecidos pelo Api   
    include 'Clientes.php'; //incluir o arquivo .php
    class ClientesService 
    {
          //Um método "get" para consulta de dados: (protocolo: "get" - buscar os dados no BD)
          // quando "$id = null" significa que pode ter ou não este parâmetro 
          public function get( $id = null )
          {
              if ($id){// se existe $id  
                 //retornar resultado do método select($id) da class Clientes            
                 return Clientes::select($id) ;
              }else{
                 //retornar resultado do método selectAll() da class Clientes 
                 return Clientes::selectAll() ;
              }

          }
          // funcão para inclusão de dados
          public function post()
          {   
              //Pegar os dados em formato JSON para incluir no BD.
              $dados = json_decode(file_get_contents('php://input'), true, 512);
              if ($dados == null){
                    throw new Exception("Falta os dados para incluir !");
              }
              return Clientes::insert($dados);              
          }
          // funcão para alteração de dados

          // funcão para alteração de dados
          public function put($id = null)
          {
              if ($id == null){
                throw new Exception("Falta o codigo !");
              }
              //Pegar as informações para atualizar no BD
              $dados = json_decode(file_get_contents('php://input'), true, 512);
              if ($dados == null){
                throw new Exception("Falta informação!");
              }
              return Clientes::update($id,$dados);              
          }
          // exclusão de dados
          public function delete($id = null)
          {
               if ($id == null){
                    throw new Exception("Falta o codigo !");
               }
               return Clientes::delete($id);
          }
    }