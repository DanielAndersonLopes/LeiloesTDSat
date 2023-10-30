/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public ProdutosDAO() {
        this.conn = new conectaDAO().connectDB();
    }

    public void cadastrarProduto(ProdutosDTO produto) {
        String sql = "insert into Produtos(nome, valor, status)" + "values(?,?,?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, produto.getNome());
            ps.setInt(2, produto.getValor());
            ps.setString(3, produto.getStatus());
            ps.execute();
            ps.close();

            JOptionPane.showMessageDialog(null, "Produto cadastrado.");
            {
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar.");
        }
    }

    public ArrayList<ProdutosDTO> listarProdutos() {
        try {
            String sql = "select * from Produtos";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));

                listagem.add(produto);
            }
            return listagem;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao acessar os dados.");
            return null;
        }
    }

    public void venderProduto(int numeroId) {
        try {
            String sql = "UPDATE Produtos SET status = 'Vendido' WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, numeroId);

            int rowsUpdated = ps.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Produto vendido!");
            } else {
                System.out.println("Produto não encontrado.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao acessar os dados.");
        }
    }

    public ArrayList<ProdutosDTO> listarProdutosVendidos() {
        try {
            String sql = "select * from Produtos WHERE status = 'Vendido'";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));

                listagem.add(produto);
            }
            return listagem;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao acessar os dados.");
            return null;
        }
    }
}
