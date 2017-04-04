package model;

import resources.BaseColumns;
import websource.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dn on 21/03/17.
 */
public class Cliente extends Persona {

    private int telefono;
    private String posizioneCorrente, codiceCliente;
    private Connection connection;

    public Cliente(String codiceCliente, String nome, String cognome, Date dataDiNascita, int telefono, String posizioneCorrente) {
        super(nome, cognome, dataDiNascita);
        this.codiceCliente = codiceCliente;
        this.telefono = telefono;
        this.posizioneCorrente = posizioneCorrente;
    }

    public String getCodiceCliente() {return codiceCliente;}

    public Cliente setCodiceCliente(String codiceCliente) {this.codiceCliente = codiceCliente;  return this;}

    public int getTelefono() {
        return telefono;
    }

    public Cliente setTelefono(int telefono) {this.telefono = telefono; return this;}

    public String getPosizioneCorrente() {
        return posizioneCorrente;
    }

    public Cliente setPosizioneCorrente(String posizioneCorrente) {this.posizioneCorrente = posizioneCorrente; return this;}

    public void inserisciCliente(){
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        DatabaseManager db = DatabaseManager.getInstance();
        connection = db.getConnection();
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement("INSERT INTO "+ BaseColumns.TAB_CLIENTE+
                    "("+BaseColumns.IDENTIFICATIVO_CLIENTE+","+BaseColumns.NOME_PERSONA+","+BaseColumns.COGNOME_PERSONA+","+
                    BaseColumns.DATA_DI_NASCITA_PERSONA+","+BaseColumns.TELEFONO+")"+" VALUES(?,?,?,?,?)");
            statement.setString(1,this.getCodiceCliente());
            statement.setString(2,this.getNome());
            statement.setString(3, this.getCognome());
            statement.setDate(4, java.sql.Date.valueOf(df.format(this.getDataDiNascita())));
            statement.setInt(5,this.getTelefono());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.print("Exception of SQL");
            return;
        }
    }

    @Override
    public String toString() {return "Cliente: " + "telefono:" + telefono + ", posizione corrente:'" + posizioneCorrente + '\'';}
}
