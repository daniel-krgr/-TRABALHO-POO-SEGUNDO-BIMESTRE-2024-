/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.pet.dogui.poo.services;

import br.unipar.pet.dogui.poo.domain.Cachorro;
import br.unipar.pet.dogui.poo.exceptions.NegocioException;
import br.unipar.pet.dogui.poo.respositories.CachorroRepository;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Daniel
 */
//Responsavel pela logica de negocio    
public class CachorroService {
    
    //Insert de uma nova cachorro
    public Cachorro insert(Cachorro cachorro) throws SQLException, NegocioException {
        
        validate(cachorro);
        
        CachorroRepository cachorroRepository = new CachorroRepository();
        cachorro = cachorroRepository.insert(cachorro);
        
        return cachorro;
    }
    
    //Update de cachorro
    public Cachorro edit(Cachorro cachorro) throws SQLException, NegocioException {
        
        validate(cachorro);
        validateUpdate(cachorro);
        
        CachorroRepository cachorroRepository = new CachorroRepository();
        cachorro = cachorroRepository.update(cachorro);
        
        return cachorro;
        
    }
    
    //Busca um unico cachorro pela pk 
    public Cachorro findById(int id) throws SQLException {
        
        CachorroRepository cachorroRepository = new CachorroRepository();
        Cachorro cachorro = cachorroRepository.findById(id);
        
        return cachorro;
        
    }
    
    //Busca todos os Cachorros cadastrados no banco
    public ArrayList<Cachorro> findAll() throws SQLException {
        
        CachorroRepository cachorroRepository = new CachorroRepository();
        ArrayList<Cachorro> resultado = cachorroRepository.findAll();
        
        return resultado;
    }
    
    //Valida os atributos do Cachorro
    private void validate(Cachorro cachorro) throws NegocioException {
        
        // Validação nome
        if (cachorro.getNome()== null) {
            throw new NegocioException(
                    "O nome do Cachorro deve ser Informado.");
        }
        if (cachorro.getNome().isBlank()) {
            throw new NegocioException(
                    "O nome do Cachorro deve ser Informado.");
        }
        if (cachorro.getNome().length() < 2) {
            throw new NegocioException("O nome do Cachorro "
                    + "deve possuir no mínimo 2 caracteres");
        }
        if (cachorro.getNome().length() > 60) {
            throw new NegocioException("O nome do Cachorro "
                    + "não deve possuir mais do que 60 caracteres");
        }
        
        // Validação tamanho
        if (cachorro.getTamanho() == null) {
            throw new NegocioException("O tamanho do Cachorro deve ser informado.");
        }
        try {
            if (cachorro.getTamanho() <= 0) {
                throw new NegocioException("O tamanho do Cachorro deve ser maior que zero.");
            }
            if (cachorro.getTamanho() > 2) {
                throw new NegocioException("O tamanho máximo do Cachorro é 2 metros.");
            }
        } catch (NumberFormatException e) {
            throw new NegocioException("O tamanho do Cachorro deve ser um número válido.");
        }
        
        // Validação stPerfume
        if (cachorro.isStPerfume() != true && cachorro.isStPerfume() != false) {
            throw new NegocioException(
                    "A informação de perfume do Cachorro deve ser informada como verdadeiro ou falso.");
        }
        
        // Validação data de nascimento
        if (cachorro.getDtNascimento() == null) {
            throw new NegocioException("A data de nascimento do Cachorro deve ser informada.");
        }
        if (cachorro.getDtNascimento().after(new Date())) {
            throw new NegocioException("A data de nascimento do Cachorro não pode ser futura.");
        }
        
        // Validação raça
        if (cachorro.getRaca() == null || cachorro.getRaca().getId() == 0) {
            throw new NegocioException("A raça do Cachorro deve ser informada.");
        }
        
        // Validação pelagem
        if (cachorro.getPelagem() == null || cachorro.getPelagem().getId() == 0) {
            throw new NegocioException("A pelagem do Cachorro deve ser informada.");
        }
        
        // Validação cor
        if (cachorro.getCor() == null || cachorro.getCor().getId() == 0) {
            throw new NegocioException("A cor do Cachorro deve ser informada.");
        }

    }
    
    private void validateUpdate(Cachorro cachorro) throws NegocioException {
        if (cachorro.getId() == 0) {
            throw new NegocioException(
                    "Informe um código válido para atualização do cachorro.");
        }
    }
    
    public void delete(int id) throws SQLException {
        CachorroRepository cachorroRepository = new CachorroRepository();
        cachorroRepository.delete(id);
    }
    
}

