public class Exercicio1 {
    public static void main(String[] args) {
        String[] especialidades = {
            "Radiologia/2030/50",
            "Oftalmologia/2500/70",
            "Pediatria/2700/75"
        };

        String[] medicos = {
            "Vasco Santana/radiologia/15/10",
            "Laura Alves/oftalmologia/5/7",
            "António Silva/oftalmologia/12/5"
        };

        printMedicos(medicos, especialidades);
        printEspecialidades(medicos, especialidades);
    }

    // Função que percorre a lista de médicos e imprime o salário de cada um
    public static void printMedicos(String[] medicos, String[] especialidades) {
        for (String medico : medicos) {
            String[] dadosMedico = medico.split("/");
            String nome = dadosMedico[0];
            String especialidade = dadosMedico[1];
            int anosServico = Integer.parseInt(dadosMedico[2]);
            int horasExtra = Integer.parseInt(dadosMedico[3]);

            double salario = calcularSalarioMedico(especialidades, especialidade, anosServico, horasExtra);
            System.out.printf("%s: %.1f€\n", nome, salario);
        }
        System.out.println();
    }

    // Função que percorre a lista de especialidades e imprime o salário total de cada uma
    public static void printEspecialidades(String[] medicos, String[] especialidades) {
        for (String especialidade : especialidades) {
            String nomeEspecialidade = especialidade.split("/")[0];
            double totalSalario = calcularSalarioEspecialidade(medicos, especialidades, nomeEspecialidade);
            System.out.printf("%s: %.1f€\n", nomeEspecialidade, totalSalario);
        }
    }

    // Função que calcula o salário de um médico
    public static double calcularSalarioMedico(String[] especialidades, String especialidade, int anosServico, int horasExtra) {
        for (String esp : especialidades) {
            String[] dadosEspecialidade = esp.split("/");
            if (dadosEspecialidade[0].equalsIgnoreCase(especialidade)) {
                double salarioBase = Double.parseDouble(dadosEspecialidade[1]);
                double custoHoraExtra = Double.parseDouble(dadosEspecialidade[2]);

                int anosServicoIncremento = anosServico / 5;
                double componenteFixa = salarioBase * (1 + 0.04 * anosServicoIncremento);
                double componenteVariavel = custoHoraExtra * horasExtra;

                return componenteFixa + componenteVariavel;
            }
        }
        return 0;
    }

    // Função que calcula o salário total de todos os médicos de uma especialidade
    public static double calcularSalarioEspecialidade(String[] medicos, String[] especialidades, String especialidade) {
        double totalSalario = 0;
        for (String medico : medicos) {
            String[] dadosMedico = medico.split("/");
            String especialidadeMedico = dadosMedico[1];
            int anosServico = Integer.parseInt(dadosMedico[2]);
            int horasExtra = Integer.parseInt(dadosMedico[3]);

            if (especialidadeMedico.equalsIgnoreCase(especialidade)) 
                totalSalario += calcularSalarioMedico(especialidades, especialidade, anosServico, horasExtra);
        }
        return totalSalario;
    }
}