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

        funcao(medicos, especialidades);
    }

    public static void funcao(String[] medicos , String[] especialidades){
        double[] salariosEspecialidades = new double[especialidades.length];  // Tabela com os salários totais de cada especialidade
        String[] nomesEspecialidades = new String[especialidades.length];     // Tabela com os nomes das especialidades

        for (int i = 0; i < especialidades.length; i++)
            nomesEspecialidades[i] = especialidades[i].split("/")[0];

        // Percorremos a lista dos médicos
        for (String medico : medicos) {
            String[] dadosMedico = medico.split("/");
            String nome = dadosMedico[0];
            String especialidade = dadosMedico[1].substring(0, 1).toUpperCase() + dadosMedico[1].substring(1);
            int anos = Integer.parseInt(dadosMedico[2]);
            int horasExtras = Integer.parseInt(dadosMedico[3]);

            // Percorremos a lista das especialidades à procura da especialidade do médico
            for (int i = 0; i < especialidades.length; i++) {
                if (nomesEspecialidades[i].equals(especialidade)) {
                    String[] dadosEspecialidade = especialidades[i].split("/");
                    double salarioBase = Double.parseDouble(dadosEspecialidade[1]);
                    double custoHoraExtra = Double.parseDouble(dadosEspecialidade[2]);

                    double componenteFixa = salarioBase * Math.pow(1.04, anos / 5);
                    double componenteVariavel = horasExtras * custoHoraExtra;
                    double salario = componenteFixa + componenteVariavel;

                    salariosEspecialidades[i] += salario;

                    System.out.printf("%s: %.1f€\n", nome, salario);
                    break; 
                }
            }
        }
        System.out.println();
        for (int i = 0; i < especialidades.length; i++) 
            if (salariosEspecialidades[i] > 0)
                System.out.printf("%s: %.1f€\n", nomesEspecialidades[i], salariosEspecialidades[i]);
    }
}