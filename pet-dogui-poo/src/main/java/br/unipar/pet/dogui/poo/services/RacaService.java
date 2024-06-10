/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.pet.dogui.poo.services;

import br.unipar.pet.dogui.poo.domain.Raca;
import br.unipar.pet.dogui.poo.exceptions.NegocioException;
import br.unipar.pet.dogui.poo.respositories.RacaRepository;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Daniel
 */
//Responsavel pela logica de negocio
public class RacaService {
    
    //Insert de uma nova raca
    public Raca insert(Raca raca) throws SQLException, NegocioException {
        
        validate(raca);
        
        RacaRepository racaRepository = new RacaRepository();
        raca = racaRepository.insert(raca);
        
        return raca;
    }
    
    //Update de raca
    public Raca edit(Raca raca) throws SQLException, NegocioException {
        
        validate(raca);
        validateUpdate(raca);
        
        RacaRepository racaRepository = new RacaRepository();
        raca = racaRepository.update(raca);
        
        return raca;
        
    }
    
    //Busca uma unica raca pela pk 
    public Raca findById(int id) throws SQLException {
        
        RacaRepository racaRepository = new RacaRepository();
        Raca raca = racaRepository.findById(id);
        
        return raca;
        
    }
    
    //Busca todas as Raças cadastradas no banco
    public ArrayList<Raca> findAll() throws SQLException {
        
        RacaRepository racaRepository = new RacaRepository();
        ArrayList<Raca> resultado = racaRepository.findAll();
        
        return resultado;
    }
    
    //Valida os atributos de Raça
    private void validate(Raca raca) throws NegocioException {
        if (raca.getDescricao() == null) {
            throw new NegocioException("A descrição da Raça deve ser "
                    + "Informada.");
        }
        if (raca.getDescricao().isBlank()) {
            throw new NegocioException("A descrição da Raça "
                    + "deve ser Informada.");
        }
        if (raca.getDescricao().length() < 3) {
            throw new NegocioException("A descrição da Raça "
                    + "deve possuir no mínimo 3 caracteres");
        }
        if (raca.getDescricao().length() > 60) {
            throw new NegocioException("A descrição da Raça "
                    + "não deve possuir mais do que 60 caracteres");
        }
    }
    
    private void validateUpdate(Raca raca) throws NegocioException {
        if (raca.getId() == 0) {
            throw new NegocioException("Informe um Código Válido "
                    + "para atualização da raca");
        }
    }
    
    public void delete(int id) throws SQLException {
        RacaRepository racaRepository = new RacaRepository();
        racaRepository.delete(id);
    }
    
}
