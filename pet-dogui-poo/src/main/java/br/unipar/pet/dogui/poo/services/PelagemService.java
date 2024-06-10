/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.pet.dogui.poo.services;

import br.unipar.pet.dogui.poo.domain.Pelagem;
import br.unipar.pet.dogui.poo.exceptions.NegocioException;
import br.unipar.pet.dogui.poo.respositories.PelagemRepository;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Daniel
 */
//Responsavel pela logica de negocio
public class PelagemService {
    
    //Insert de uma nova pelagem
    public Pelagem insert(Pelagem pelagem) throws SQLException, NegocioException {
        
        validate(pelagem);
        
        PelagemRepository pelagemRepository = new PelagemRepository();
        pelagem = pelagemRepository.insert(pelagem);
        
        return pelagem;
    }
    
    //Update de pelagem
    public Pelagem edit(Pelagem pelagem) throws SQLException, NegocioException {
        
        validate(pelagem);
        validateUpdate(pelagem);
        
        PelagemRepository pelagemRepository = new PelagemRepository();
        pelagem = pelagemRepository.update(pelagem);
        
        return pelagem;
        
    }
    
    //Busca uma unica pelagem pela pk 
    public Pelagem findById(int id) throws SQLException {
        
        PelagemRepository pelagemRepository = new PelagemRepository();
        Pelagem pelagem = pelagemRepository.findById(id);
        
        return pelagem;
        
    }
    
    //Busca todas as pelagens cadastradas no banco
    public ArrayList<Pelagem> findAll() throws SQLException {
        
        PelagemRepository pelagemRepository = new PelagemRepository();
        ArrayList<Pelagem> resultado = pelagemRepository.findAll();
        
        return resultado;
    }
    
    //Valida os atributos de pelagem
    private void validate(Pelagem pelagem) throws NegocioException {
        if (pelagem.getDescricao() == null) {
            throw new NegocioException("A descrição da Pelagem deve ser "
                    + "Informada.");
        }
        if (pelagem.getDescricao().isBlank()) {
            throw new NegocioException("A descrição da Pelagem "
                    + "deve ser Informada.");
        }
        if (pelagem.getDescricao().length() < 3) {
            throw new NegocioException("A descrição da Pelagem "
                    + "deve possuir no mínimo 3 caracteres");
        }
        if (pelagem.getDescricao().length() > 60) {
            throw new NegocioException("A descrição da Pelagem "
                    + "não deve possuir mais do que 60 caracteres");
        }
    }
    
    private void validateUpdate(Pelagem pelagem) throws NegocioException {
        if (pelagem.getId() == 0) {
            throw new NegocioException("Informe um Código Válido "
                    + "para atualização da Pelagem");
        }
    }
    
    public void delete(int id) throws SQLException {
        PelagemRepository pelagemRepository = new PelagemRepository();
        pelagemRepository.delete(id);
    }
    
}

