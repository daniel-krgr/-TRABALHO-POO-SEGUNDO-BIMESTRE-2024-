/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package br.unipar.pet.dogui.poo;

import br.unipar.pet.dogui.poo.domain.Cachorro;
import br.unipar.pet.dogui.poo.domain.Cor;
import br.unipar.pet.dogui.poo.domain.Pelagem;
import br.unipar.pet.dogui.poo.domain.Raca;
import br.unipar.pet.dogui.poo.exceptions.NegocioException;
import br.unipar.pet.dogui.poo.services.CachorroService;
import br.unipar.pet.dogui.poo.services.CorService;
import br.unipar.pet.dogui.poo.services.PelagemService;
import br.unipar.pet.dogui.poo.services.RacaService;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andersonbosing
 */
public class PetDoguiPoo {

    public static void main(String[] args) {
        
        try {
            //Cor
            CorService corService = new CorService();
            Cor c = new Cor();
            c.setDescricao("Branco com pontos pretos");
            
            corService.insert(c);
            ArrayList<Cor> resultado = corService.findAll();
            System.out.println(resultado.toString());
            
            
            //Pelagem
            PelagemService pelagemService = new PelagemService();
            Pelagem p = new Pelagem();
            p.setDescricao("Curto");
            
            pelagemService.insert(p);
            ArrayList<Pelagem> resultado1 = pelagemService.findAll();
            System.out.println(resultado1.toString());

            
            //Raça
            RacaService racaService = new RacaService();
            Raca r = new Raca();
            r.setDescricao("Dalmata");
            
            racaService.insert(r);
            ArrayList<Raca> resultado2 = racaService.findAll();
            System.out.println(resultado2.toString());
           
            
            //Data
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
            Date d = formato.parse("17/08/2012");

            
            //Cachorro
            CachorroService cachorroService = new CachorroService();
            Cachorro cao = new Cachorro();
            cao.setNome("Sérjão");
            cao.setCor(c);
            cao.setDtNascimento(d);
            cao.setPelagem(p);
            cao.setRaca(r);
            cao.setStPerfume(false);
            cao.setTamanho(0.7);
            
            cachorroService.insert(cao);
            ArrayList<Cachorro> resultado3 = cachorroService.findAll();
            System.out.println(resultado3.toString());
            
            
        } catch (SQLException ex) {
            System.out.println("Ops, algo deu errado com o banco de dados\n"+ex.getMessage());
        } catch (NegocioException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {    
            System.out.println("Ops, algo deu errado contate o suporte\n"+ex.getMessage());
        }
        
        
        
        
    }
}
