import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Classe Aluno
class Aluno {
    String nome;
    String matricula;
    String dataNascimento;
    String nomeMae;

    public Aluno(String nome, String matricula, String dataNascimento, String nomeMae) {
        this.nome = nome;
        this.matricula = matricula;
        this.dataNascimento = dataNascimento;
        this.nomeMae = nomeMae;
    }

    @Override
    public String toString() {
        return "Aluno: " + nome + " | Matrícula: " + matricula + " | Data de Nascimento: " + dataNascimento + " | Nome da Mãe: " + nomeMae;
    }
}

// Classe Professor
class Professor {
    String nome;
    String matricula;
    String grauInstrucao;
    double salarioBase;

    public Professor(String nome, String matricula, String grauInstrucao, double salarioBase) {
        this.nome = nome;
        this.matricula = matricula;
        this.grauInstrucao = grauInstrucao;
        this.salarioBase = salarioBase;
    }

    @Override
    public String toString() {
        return "Professor: " + nome + " | Matrícula: " + matricula + " | Grau de Instrução: " + grauInstrucao + " | Salário: R$ " + salarioBase;
    }
}

// Classe Turma
class Turma {
    String codigo;
    String nomeTurma;
    String sala;
    String horario;
    String tipo;
    List<Aluno> alunos = new ArrayList<>();
    List<String> materiais = new ArrayList<>();

    public Turma(String codigo, String nomeTurma, String sala, String horario, String tipo) {
        this.codigo = codigo;
        this.nomeTurma = nomeTurma;
        this.sala = sala;
        this.horario = horario;
        this.tipo = tipo;
    }

    public void adicionarAluno(Aluno aluno) {
        alunos.add(aluno);
    }

    public void adicionarMaterial(String material) {
        materiais.add(material);
    }

    @Override
    public String toString() {
        return "Turma: " + nomeTurma + " | Código: " + codigo + " | Sala: " + sala + " | Horário: " + horario + " | Tipo: " + tipo;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public List<String> getMateriais() {
        return materiais;
    }
}

// Classe Escola
class Escola {
    List<Aluno> alunos = new ArrayList<>();
    List<Professor> professores = new ArrayList<>();
    List<Turma> turmas = new ArrayList<>();

    public void cadastrarAluno(Aluno aluno, String codigoTurma) {
        alunos.add(aluno);
        for (Turma turma : turmas) {
            if (turma.codigo.equals(codigoTurma)) {
                turma.adicionarAluno(aluno);
                System.out.println("Aluno " + aluno.nome + " cadastrado na turma " + turma.nomeTurma);
                return;
            }
        }
        System.out.println("Turma não encontrada. O aluno foi cadastrado, mas não foi associado a nenhuma turma.");
    }

    public void cadastrarProfessor(Professor professor) {
        professores.add(professor);
    }

    public void cadastrarTurma(Turma turma) {
        turmas.add(turma);
    }

    public void listarAlunosPorTurma(String codigoTurma) {
        for (Turma turma : turmas) {
            if (turma.codigo.equals(codigoTurma)) {
                System.out.println("Alunos da Turma " + turma.nomeTurma + ":");
                for (Aluno aluno : turma.getAlunos()) {
                    System.out.println(aluno);
                }
                return;
            }
        }
        System.out.println("Turma não encontrada.");
    }

    public void listarMateriaisPorTurma(String codigoTurma) {
        for (Turma turma : turmas) {
            if (turma.codigo.equals(codigoTurma)) {
                System.out.println("Materiais da Turma " + turma.nomeTurma + ":");
                for (String material : turma.getMateriais()) {
                    System.out.println(material);
                }
                return;
            }
        }
        System.out.println("Turma não encontrada.");
    }

    public void emitirBoletimPagamentoProfessor() {
        for (Professor professor : professores) {
            System.out.println("Boletim de Pagamento - Professor: " + professor.nome + " | Salário Base: R$ " + professor.salarioBase);
        }
    }
}

// Classe principal
public class SistemaEscolar {
    public static void main(String[] args) {
        Escola escola = new Escola();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n---- Menu Principal ----");
            System.out.println("1. Cadastrar Aluno");
            System.out.println("2. Cadastrar Professor");
            System.out.println("3. Cadastrar Turma");
            System.out.println("4. Listar Alunos por Turma");
            System.out.println("5. Listar Materiais por Turma");
            System.out.println("6. Emitir Boletim de Pagamento dos Professores");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();  // Consumir a nova linha

            switch (opcao) {
                case 1:
                    System.out.println("---- Cadastro de Aluno ----");
                    System.out.print("Nome: ");
                    String nomeAluno = scanner.nextLine();
                    System.out.print("Matrícula: ");
                    String matriculaAluno = scanner.nextLine();
                    System.out.print("Data de Nascimento: ");
                    String dataNascimentoAluno = scanner.nextLine();
                    System.out.print("Nome da Mãe: ");
                    String nomeMaeAluno = scanner.nextLine();
                    System.out.print("Código da Turma: ");
                    String codigoTurmaAluno = scanner.nextLine();
                    Aluno aluno = new Aluno(nomeAluno, matriculaAluno, dataNascimentoAluno, nomeMaeAluno);
                    escola.cadastrarAluno(aluno, codigoTurmaAluno);
                    break;

                case 2:
                    System.out.println("---- Cadastro de Professor ----");
                    System.out.print("Nome: ");
                    String nomeProfessor = scanner.nextLine();
                    System.out.print("Matrícula: ");
                    String matriculaProfessor = scanner.nextLine();
                    System.out.print("Grau de Instrução: ");
                    String grauInstrucao = scanner.nextLine();
                    System.out.print("Salário Base: ");
                    double salarioBase = scanner.nextDouble();
                    scanner.nextLine();  // Consumir a nova linha
                    Professor professor = new Professor(nomeProfessor, matriculaProfessor, grauInstrucao, salarioBase);
                    escola.cadastrarProfessor(professor);
                    System.out.println("Professor cadastrado com sucesso!");
                    break;

                case 3:
                    System.out.println("---- Cadastro de Turma ----");
                    System.out.print("Código da Turma: ");
                    String codigoTurma = scanner.nextLine();
                    System.out.print("Nome da Turma: ");
                    String nomeTurma = scanner.nextLine();
                    System.out.print("Sala: ");
                    String sala = scanner.nextLine();
                    System.out.print("Horário: ");
                    String horario = scanner.nextLine();
                    System.out.print("Tipo (A, B, C): ");
                    String tipo = scanner.nextLine();
                    Turma turma = new Turma(codigoTurma, nomeTurma, sala, horario, tipo);
                    escola.cadastrarTurma(turma);
                    System.out.println("Turma cadastrada com sucesso!");
                    break;

                case 4:
                    System.out.println("---- Listar Alunos por Turma ----");
                    System.out.print("Código da Turma: ");
                    String codigoTurmaListar = scanner.nextLine();
                    escola.listarAlunosPorTurma(codigoTurmaListar);
                    break;

                case 5:
                    System.out.println("---- Listar Materiais por Turma ----");
                    System.out.print("Código da Turma: ");
                    String codigoTurmaMateriais = scanner.nextLine();
                    escola.listarMateriaisPorTurma(codigoTurmaMateriais);
                    break;

                case 6:
                    System.out.println("---- Emitir Boletim de Pagamento dos Professores ----");
                    escola.emitirBoletimPagamentoProfessor();
                    break;

                case 7:
                    System.out.println("Saindo do sistema...");
                    break;

                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (opcao != 7);

        scanner.close();
    }
}
