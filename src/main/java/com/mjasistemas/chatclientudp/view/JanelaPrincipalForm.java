/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mjasistemas.chatclientudp.view;

import com.mjasistemas.chatclientudp.model.pessoa.Pessoa;
import com.mjasistemas.chatclientudp.model.pessoa.TipoPessoaEnum;
import com.mysql.jdbc.log.Log;

/**
 *
 * @author marcio
 */
public class JanelaPrincipalForm extends javax.swing.JFrame {

    private final Pessoa pessoa;
    /**
     * Creates new form JanelaPrincipalForm
     */
    public JanelaPrincipalForm(Pessoa pessoa) {
        this.pessoa = pessoa;
        initComponents();
        habilitarOpcoes();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new javax.swing.JDesktopPane();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        mnuSalasAbertas = new javax.swing.JMenuItem();
        mnuGerenciar = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        cutMenuItem = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        contentMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();
        mnuSair = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        fileMenu.setMnemonic('f');
        fileMenu.setText("Salas");

        mnuSalasAbertas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        mnuSalasAbertas.setText("Abertas");
        mnuSalasAbertas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuSalasAbertasActionPerformed(evt);
            }
        });
        fileMenu.add(mnuSalasAbertas);

        mnuGerenciar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        mnuGerenciar.setText("Gerenciar");
        mnuGerenciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuGerenciarActionPerformed(evt);
            }
        });
        fileMenu.add(mnuGerenciar);

        menuBar.add(fileMenu);

        editMenu.setMnemonic('e');
        editMenu.setText("Configurações");

        cutMenuItem.setMnemonic('t');
        cutMenuItem.setText("Fontes & cores");
        editMenu.add(cutMenuItem);

        jMenuItem3.setText("Configurar");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        editMenu.add(jMenuItem3);

        menuBar.add(editMenu);

        helpMenu.setMnemonic('h');
        helpMenu.setText("Ajuda");

        contentMenuItem.setMnemonic('c');
        contentMenuItem.setText("Conteúdo");
        helpMenu.add(contentMenuItem);

        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("Sobre");
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        mnuSair.setMnemonic('S');
        mnuSair.setText("Sair");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Alterar conta");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        mnuSair.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem2.setText("Encerrar sistema");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        mnuSair.add(jMenuItem2);

        menuBar.add(mnuSair);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 861, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void mnuSalasAbertasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuSalasAbertasActionPerformed
        
    }//GEN-LAST:event_mnuSalasAbertasActionPerformed

    private void mnuGerenciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuGerenciarActionPerformed
        GerenciarSessoesForm gerenciarSessoesForm = new GerenciarSessoesForm(this,pessoa);
        gerenciarSessoesForm.setVisible(true);
        desktopPane.add(gerenciarSessoesForm);
    }//GEN-LAST:event_mnuGerenciarActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        new LoginForm().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem3ActionPerformed

   private void habilitarOpcoes() {
        mnuGerenciar.setEnabled(pessoa.getTipo()==TipoPessoaEnum.ADMINISTRADOR);
       // mnuSalasAbertas.setEnabled(usuario.getTipo()==TipoPessoaEnum.ADMINISTRADOR);
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem contentMenuItem;
    private javax.swing.JMenuItem cutMenuItem;
    public javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem mnuGerenciar;
    private javax.swing.JMenu mnuSair;
    private javax.swing.JMenuItem mnuSalasAbertas;
    // End of variables declaration//GEN-END:variables

    

}
