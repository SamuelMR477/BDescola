package resources.db;

import util.CommonConstants;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Scanner;

// JDBC - Java Database Connectivity
// Essa clase será a porta para acessar o banco de dados MySQL
public class JDBC {
    Scanner sc = new Scanner(System.in);

    // Cadastrando novo usuário ao banco de dados
    // true = cadastro bem sucedido
    // false = cadastro mal sucedido
    public static boolean cadastrar(String nomeTabela) {
        if (nomeTabela == "alunos") {
            try {
                // Primeiro checa se o usuário e o email já existe no banco de dados
                String nomeAluno = JOptionPane.showInputDialog("Digite o nome do aluno");

                String sobrenomeAluno = JOptionPane.showInputDialog("Digite o sobrenome do aluno");

                if (checarAluno(nomeAluno, sobrenomeAluno)) {
                    JOptionPane.showMessageDialog(null, "Aluno já existe", "Erro", JOptionPane.INFORMATION_MESSAGE);

                    return false;
                }

                String idade = JOptionPane.showInputDialog("Digite a idade do aluno");
                if (idade == null || idade.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Digite a idade do aluno", "Erro", JOptionPane.INFORMATION_MESSAGE);

                    return false;
                }

                String telefone = JOptionPane.showInputDialog("Digite o telefone do aluno");
                if (telefone == null || telefone.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Digite o telefone do aluno", "Erro", JOptionPane.INFORMATION_MESSAGE);

                    return false;
                } else if(telefone.length() < 11){
                    JOptionPane.showMessageDialog(null, "Digite um telefone válido", "Erro", JOptionPane.INFORMATION_MESSAGE);

                    return false;
                }

                String sala = JOptionPane.showInputDialog("Digite a sala em que o aluno estuda");
                if (sala == null || sala.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Digite a sala do aluno", "Erro", JOptionPane.INFORMATION_MESSAGE);

                    return false;
                }

                String media_primeiro_semestre = JOptionPane.showInputDialog("Digite a média do primeiro semestre do aluno");

                String media_segundo_semestre = JOptionPane.showInputDialog("Digite a média do segundo semestre do aluno");

                if (!checarAluno(nomeAluno, sobrenomeAluno)) {
                    // se conecta ao banco de dados
                    Connection connection = DriverManager.getConnection(CommonConstants.DB_URL,
                            CommonConstants.DB_USERNAME, CommonConstants.DB_PASSWORD);

                    // criando a insert query
                    PreparedStatement insertUser = connection.prepareStatement(
                            "INSERT INTO " + CommonConstants.DB_ALUNOS_TABLE_NAME + " (nome, sobrenome, idade, telefone, sala, media_primeiro_semestre, media_segundo_semestre)" +
                                    "VALUES(?, ?, ?, ?, ?, ?, ?);"
                    );

                    // insere os parametros no insert query
                    insertUser.setString(1, nomeAluno);
                    insertUser.setString(2, sobrenomeAluno);
                    insertUser.setString(3, idade);
                    insertUser.setString(4, telefone);
                    insertUser.setString(5, sala);
                    insertUser.setString(6, media_primeiro_semestre);
                    insertUser.setString(7, media_segundo_semestre);

                    // atualiza banco de dados com novo usuário
                    insertUser.executeUpdate();
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (nomeTabela == "professor") {
            try {
                // Primeiro checa se o usuário e o email já existe no banco de dados
                String novoUsuario = JOptionPane.showInputDialog("Digite o novo Usuário");
                if (checarUsuario(novoUsuario)) {
                    JOptionPane.showMessageDialog(null, "Usuário já existe", "Erro", JOptionPane.INFORMATION_MESSAGE);

                    return false;
                } else if (novoUsuario == null || novoUsuario.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Digite um Usuário", "Erro", JOptionPane.INFORMATION_MESSAGE);

                    return false;
                }

                String novoEmail = JOptionPane.showInputDialog("Digite o novo Email");
                if (checarEmail(novoEmail)) {
                    JOptionPane.showMessageDialog(null, "Email já existe", "Erro", JOptionPane.INFORMATION_MESSAGE);

                    return false;
                } else if (novoEmail == null || novoEmail.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Digite um Email", "Erro", JOptionPane.INFORMATION_MESSAGE);

                    return false;
                }

                String novaSenha = JOptionPane.showInputDialog("Digite a nova Senha");
                if (novaSenha == null || novaSenha.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Digite uma Senha", "Erro", JOptionPane.INFORMATION_MESSAGE);

                    return false;
                }

                if (!checarUsuario(novoUsuario) && !checarEmail(novoEmail)) {
                    // se conecta ao banco de dados
                    Connection connection = DriverManager.getConnection(CommonConstants.DB_URL,
                            CommonConstants.DB_USERNAME, CommonConstants.DB_PASSWORD);

                    // criando a insert query
                    PreparedStatement insertUser = connection.prepareStatement(
                            "INSERT INTO " + CommonConstants.DB_PROFESSORES_TABLE_NAME + "(usuario, senha, email)" +
                                    "VALUES(?, ?, ?);"
                    );

                    // insere os parametros no insert query
                    insertUser.setString(1, novoUsuario);
                    insertUser.setString(2, novaSenha);
                    insertUser.setString(3, novoEmail);

                    // atualiza banco de dados com novo usuário
                    insertUser.executeUpdate();
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    // checa se o usuário já existe
    // falso - não existe nenhum usuário com este nome
    // true - existe um usuário com esse nome
    public static boolean checarUsuario(String username) {
        try {
            Connection connection = DriverManager.getConnection(CommonConstants.DB_URL,
                    CommonConstants.DB_USERNAME, CommonConstants.DB_PASSWORD);

            PreparedStatement checkUserExists = connection.prepareStatement(
                    "SELECT * FROM " + CommonConstants.DB_PROFESSORES_TABLE_NAME +
                            " WHERE USUARIO = ?"
            );
            checkUserExists.setString(1, username);

            ResultSet resultSet = checkUserExists.executeQuery();

            // Verifica se o resultset está vázio
            // Se o resultSet estiver vázio significa que não possui um usuário com o mesmo nome salvo
            if (!resultSet.isBeforeFirst()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    public static boolean checarAluno(String nome, String sobrenome){
        try {
            Connection connection = DriverManager.getConnection(CommonConstants.DB_URL,
                    CommonConstants.DB_USERNAME, CommonConstants.DB_PASSWORD);

            PreparedStatement checkAluno = connection.prepareStatement(
                    "SELECT * FROM " + CommonConstants.DB_ALUNOS_TABLE_NAME +
                            " WHERE NOME = ? AND SOBRENOME = ?"
            );

            checkAluno.setString(1, nome);
            checkAluno.setString(2, sobrenome);

            ResultSet resultSetAluno = checkAluno.executeQuery();

            // Verifica se o resultset está vázio
            // Se o resultSet estiver vázio significa que não possui um usuário com o mesmo nome salvo
            if (!resultSetAluno.isBeforeFirst()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    // checa se o usuário já existe
    // falso - não existe nenhum usuário com este nome
    // true - existe um usuário com esse nome
    public static boolean checarEmail(String email) {
        try {
            Connection connection = DriverManager.getConnection(CommonConstants.DB_URL,
                    CommonConstants.DB_USERNAME, CommonConstants.DB_PASSWORD);

            PreparedStatement checkEmailExists = connection.prepareStatement(
                    "SELECT * FROM " + CommonConstants.DB_PROFESSORES_TABLE_NAME +
                            " WHERE EMAIL = ?"
            );
            checkEmailExists.setString(1, email);

            ResultSet resultSet = checkEmailExists.executeQuery();

            // Verifica se o resultset está vázio
            // Se o resultSet estiver vázio significa que não possui um email igual
            if (!resultSet.isBeforeFirst()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    ;

    // Valida as credenciais checando se o combinação username/password existe no banco de dados
    public static boolean validarLogin(String username, String password) {
        try {
            Connection connection = DriverManager.getConnection(CommonConstants.DB_URL,
                    CommonConstants.DB_USERNAME, CommonConstants.DB_PASSWORD);

            // cria o select query
            PreparedStatement validarUsuario = connection.prepareStatement(
                    "SELECT * FROM " + CommonConstants.DB_PROFESSORES_TABLE_NAME +
                            " WHERE USUARIO = ? AND SENHA = ?"
            );
            validarUsuario.setString(1, username);
            validarUsuario.setString(2, password);

            ResultSet resultSet = validarUsuario.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    public static boolean carregarDadosdoJDBC(DefaultTableModel modeloTabela, String nomeTabela) {
        // Limpa a tabela antes de carregar novos dados
        modeloTabela.setRowCount(0); // Limpa as linhas
        modeloTabela.setColumnCount(0); // Limpa as colunas

        if (nomeTabela == "alunos") {
            try {
                Connection connection = DriverManager.getConnection(CommonConstants.DB_URL,
                        CommonConstants.DB_USERNAME, CommonConstants.DB_PASSWORD);

                // cria o select query
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM " + CommonConstants.DB_ALUNOS_TABLE_NAME);

                // Obtém os metadados da tabela
                ResultSetMetaData metaData = resultSet.getMetaData();
                int numeroColuna = metaData.getColumnCount();

                // Adicionando colunas ao modelo da tabela
                for (int i = 1; i <= numeroColuna; i++) {
                    modeloTabela.addColumn(metaData.getColumnName(i));
                }

                // Adiciona as linhas ao modelo da tabela
                while (resultSet.next()) {
                    Object[] linha = new Object[numeroColuna];
                    for (int i = 1; i <= numeroColuna; i++) {
                        linha[i - 1] = resultSet.getObject(i);
                    }
                    modeloTabela.addRow(linha);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        if (nomeTabela == "professor") {
            try {
                Connection connection = DriverManager.getConnection(CommonConstants.DB_URL,
                        CommonConstants.DB_USERNAME, CommonConstants.DB_PASSWORD);

                // cria o select query
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM " + CommonConstants.DB_PROFESSORES_TABLE_NAME);

                // Obtém os metadados da tabela
                ResultSetMetaData metaData = resultSet.getMetaData();
                int numeroColuna = metaData.getColumnCount();

                // Adicionando colunas ao modelo da tabela
                for (int i = 1; i <= numeroColuna; i++) {
                    modeloTabela.addColumn(metaData.getColumnName(i));
                }

                // Adiciona as linhas ao modelo da tabela
                while (resultSet.next()) {
                    Object[] linha = new Object[numeroColuna];
                    for (int i = 1; i <= numeroColuna; i++) {
                        linha[i - 1] = resultSet.getObject(i);
                    }
                    modeloTabela.addRow(linha);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public static boolean atualizarDados(String nomeTabela) {

        if (nomeTabela == "alunos") {
            String idStr = JOptionPane.showInputDialog("Digite a matrícula do aluno que deseja atualizar");
            if (idStr == null) return false;

            int matricula = Integer.parseInt(idStr);

            String novaIdade = JOptionPane.showInputDialog("Digite a Idade (ou pressione ENTER caso deseje manter a atual");

            String novoTelefone = JOptionPane.showInputDialog("Digite o novo Telefone (ou pressione ENTER caso deseje manter a atual");

            String novaSala = JOptionPane.showInputDialog("Digite a nova Sala (ou pressione ENTER caso deseje manter o atual");

            String novaMedia_primeiro_semestre = JOptionPane.showInputDialog("Digite a nova média do primeiro semestre (ou pressione ENTER caso deseje manter a atual");

            String novaMedia_segundo_semestre = JOptionPane.showInputDialog("Digite a nova média do segundo semestre (ou pressione ENTER caso deseje manter a atual");

            StringBuilder sql = new StringBuilder("UPDATE alunos SET ");
            boolean setComma = false;

            try {
                Connection connection = DriverManager.getConnection(CommonConstants.DB_URL,
                        CommonConstants.DB_USERNAME, CommonConstants.DB_PASSWORD);

                // Adiciona as colunas a serem atualizadas
                if (novaIdade != null && !novaIdade.isEmpty()) {
                    sql.append("idade = ?");
                    setComma = true;
                }
                if (novoTelefone != null && !novoTelefone.isEmpty()) {
                    if (setComma) sql.append(", ");
                    sql.append("telefone = ?");
                    setComma = true;
                }
                if (novaSala != null && !novaSala.isEmpty()) {
                    if (setComma) sql.append(", ");
                    sql.append("sala = ?");
                }
                if (novaMedia_primeiro_semestre != null && !novaMedia_primeiro_semestre.isEmpty()) {
                    if (setComma) sql.append(", ");
                    sql.append("media_primeiro_semestre = ?");
                }
                if (novaMedia_segundo_semestre != null && !novaMedia_segundo_semestre.isEmpty()) {
                    if (setComma) sql.append(", ");
                    sql.append("media_segundo_semestre = ?");
                }

                sql.append(" WHERE matricula = ?"); // Adiciona a cláusula WHERE

                try (PreparedStatement pstmt = connection.prepareStatement(sql.toString())) {
                    int index = 1;
                    if (novaIdade != null && !novaIdade.isEmpty()) {
                        pstmt.setString(index++, novaIdade);
                    }
                    if (novoTelefone != null && !novoTelefone.isEmpty()) {
                        pstmt.setString(index++, novoTelefone);
                    }
                    if (novaSala != null && !novaSala.isEmpty()) {
                        pstmt.setString(index++, novaSala);
                    }
                    if (novaMedia_primeiro_semestre != null && !novaMedia_primeiro_semestre.isEmpty()) {
                        pstmt.setString(index++, novaMedia_primeiro_semestre);
                    }
                    if (novaMedia_segundo_semestre != null && !novaMedia_segundo_semestre.isEmpty()) {
                        pstmt.setString(index++, novaMedia_segundo_semestre);
                    }
                    pstmt.setInt(index, matricula); // Define a matrícula do aluno

                    int linhasAfetadas = pstmt.executeUpdate();
                    if (linhasAfetadas > 0) {
                        JOptionPane.showMessageDialog(null, "Dados do aluno atualizado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Aluno não encontrado", "Erro", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        if (nomeTabela == "professor") {
            String idStr = JOptionPane.showInputDialog("Digite o ID do professor que deseja atualizar");
            if (idStr == null) return false;

            int id = Integer.parseInt(idStr);

            String novoUsuario = JOptionPane.showInputDialog("Digite o novo Usuário (ou pressione ENTER caso deseje manter o atual");

            String novaSenha = JOptionPane.showInputDialog("Digite a nova Senha (ou pressione ENTER caso deseje manter a atual");

            String novoEmail = JOptionPane.showInputDialog("Digite o novo Email (ou pressione ENTER caso deseje manter o atual");

            StringBuilder sql = new StringBuilder("UPDATE professores SET ");
            boolean setComma = false;

            try {
                Connection connection = DriverManager.getConnection(CommonConstants.DB_URL,
                        CommonConstants.DB_USERNAME, CommonConstants.DB_PASSWORD);

                // Adiciona as colunas a serem atualizadas
                if (novoUsuario != null && !novoUsuario.isEmpty()) {
                    sql.append("usuario = ?");
                    setComma = true;
                }
                if (novaSenha != null && !novaSenha.isEmpty()) {
                    if (setComma) sql.append(", ");
                    sql.append("senha = ?");
                    setComma = true;
                }
                if (novoEmail != null && !novoEmail.isEmpty()) {
                    if (setComma) sql.append(", ");
                    sql.append("email = ?");
                }

                sql.append(" WHERE id = ?"); // Adiciona a cláusula WHERE

                try (PreparedStatement pstmt = connection.prepareStatement(sql.toString())) {
                    int index = 1;
                    if (novoUsuario != null && !novoUsuario.isEmpty()) {
                        pstmt.setString(index++, novoUsuario);
                    }
                    if (novaSenha != null && !novaSenha.isEmpty()) {
                        pstmt.setString(index++, novaSenha);
                    }
                    if (novoEmail != null && !novoEmail.isEmpty()) {
                        pstmt.setString(index++, novoEmail);
                    }
                    pstmt.setInt(index, id); // Define o ID do professor

                    int linhasAfetadas = pstmt.executeUpdate();
                    if (linhasAfetadas > 0) {
                        JOptionPane.showMessageDialog(null, "Usuário atualizado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuário não encontrado", "Erro", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return true;
    }

    public static boolean excluirDados(String nomeTabela) {

        if (nomeTabela == "alunos") {
            String idStr = JOptionPane.showInputDialog("Digite a matrícula do aluno que deseja excluir");
            if (idStr == null) return false;

            int matricula = Integer.parseInt(idStr);

            try {
                Connection connection = DriverManager.getConnection(CommonConstants.DB_URL,
                        CommonConstants.DB_USERNAME, CommonConstants.DB_PASSWORD);

                // Primeiro, verifique se a Matrícula existe
                PreparedStatement verificarMatricula = connection.prepareStatement(
                        "SELECT * FROM " + CommonConstants.DB_ALUNOS_TABLE_NAME + " WHERE matricula = ?"
                );
                verificarMatricula.setInt(1, matricula);
                ResultSet resultSet = verificarMatricula.executeQuery();

                // Se o resultSet estiver vazio, significa que a Matrícula não existe
                if (!resultSet.isBeforeFirst()) {
                    JOptionPane.showMessageDialog(null, "Matrícula não existe", "Erro", JOptionPane.INFORMATION_MESSAGE);
                    return false;
                }

                // Agora, se o ID existe, execute o DELETE
                PreparedStatement deletarDados = connection.prepareStatement(
                        "DELETE FROM " + CommonConstants.DB_ALUNOS_TABLE_NAME + " WHERE matricula = ?"
                );
                deletarDados.setInt(1, matricula);
                int linhasAfetadas = deletarDados.executeUpdate(); // Use executeUpdate()

                if (linhasAfetadas > 0) {
                    JOptionPane.showMessageDialog(null, "Aluno excluído com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        if (nomeTabela == "professor") {
            String idStr = JOptionPane.showInputDialog("Digite o ID do professor que deseja excluir");
            if (idStr == null) return false;

            int id = Integer.parseInt(idStr);

            try {
                Connection connection = DriverManager.getConnection(CommonConstants.DB_URL,
                        CommonConstants.DB_USERNAME, CommonConstants.DB_PASSWORD);

                // Primeiro, verifique se o ID existe
                PreparedStatement verificarId = connection.prepareStatement(
                        "SELECT * FROM " + CommonConstants.DB_PROFESSORES_TABLE_NAME + " WHERE id = ?"
                );
                verificarId.setInt(1, id);
                ResultSet resultSet = verificarId.executeQuery();

                // Se o resultSet estiver vazio, significa que o ID não existe
                if (!resultSet.isBeforeFirst()) {
                    JOptionPane.showMessageDialog(null, "ID não existe", "Erro", JOptionPane.INFORMATION_MESSAGE);
                    return false;
                }

                // Agora, se o ID existe, execute o DELETE
                PreparedStatement deletarDados = connection.prepareStatement(
                        "DELETE FROM " + CommonConstants.DB_PROFESSORES_TABLE_NAME + " WHERE id = ?"
                );
                deletarDados.setInt(1, id);
                int linhasAfetadas = deletarDados.executeUpdate(); // Use executeUpdate()

                if (linhasAfetadas > 0) {
                    JOptionPane.showMessageDialog(null, "Professor excluído com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    return true;
}
}

