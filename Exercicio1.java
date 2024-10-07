public class teste {
    public static void main(String[] args) {

        //cenas do enunciado
        String[] especialidades = {
            "Radiologia/2030/50",
            "Oftalmologia/2500/70",
            "Pediatria/2700/75"
        };

        String[] medicos = {
            "Vasco Santana/Radiologia/15/10",
            "Laura Alves/Oftalmologia/5/7",
            "António Silva/Oftalmologia/12/5"
        };

//--------------------------------- medicos ---------------------------------//
        String[] resultadosMedicos = calcularSalariosMedicos(medicos, especialidades);
        for (String resultado : resultadosMedicos) {
            System.out.println(resultado);
        }
//--------------------------------- medicos ---------------------------------//


        System.out.println(); //cheio de sono fds


//--------------------------------- especialidades ---------------------------------//
        double[] resultadosEspecialidades = calcularSalariosPorEspecialidade(medicos, especialidades);
        String[] especialidadeNomes = extrairNomesEspecialidades(especialidades);
        for (int i = 0; i < especialidadeNomes.length; i++) {
            System.out.printf("%s: %.1f€%n", especialidadeNomes[i], resultadosEspecialidades[i]);
        }
    }
//--------------------------------- especialidades ---------------------------------//





//--------------------------------- organizar/sacar da tabela ---------------------------------//
    //organizar salarios medicos
    public static String[] calcularSalariosMedicos(String[] medicos, String[] especialidades) {
        String[] resultados = new String[medicos.length];

        for (int i = 0; i < medicos.length; i++) {
            
            //sacar a info medico
            String[] partes = medicos[i].split("/");
            String nome = partes[0];
            String especialidade = partes[1];
            
            int anosServico = Integer.parseInt(partes[2]);
            int horasExtra = Integer.parseInt(partes[3]);

            double salarioBase = obterSalarioBase(especialidade, especialidades);
            double salarioFixo = calcularSalarioFixo(salarioBase, anosServico);
            double salarioVariavel = horasExtra * obterCustoHoraExtra(especialidade, especialidades);
            double salarioTotal = salarioFixo + salarioVariavel;

            resultados[i] = String.format("%s: %.1f€", nome, salarioTotal);
        }

        return resultados;
    }


        //organizar salario especialidade
        public static double[] calcularSalariosPorEspecialidade(String[] medicos, String[] especialidades) {
            double[] totalPorEspecialidade = new double[especialidades.length];
    
            for (String medico : medicos) {
                String[] partes = medico.split("/");
                String especialidade = partes[1];
                int anosServico = Integer.parseInt(partes[2]);
                int horasExtra = Integer.parseInt(partes[3]);
    
                double salarioBase = obterSalarioBase(especialidade, especialidades);
                double salarioFixo = calcularSalarioFixo(salarioBase, anosServico);
                double salarioVariavel = horasExtra * obterCustoHoraExtra(especialidade, especialidades);
                double salarioTotal = salarioFixo + salarioVariavel;
    
                // salario total na especialidade
                for (int j = 0; j < especialidades.length; j++) {
                    if (especialidades[j].toLowerCase().startsWith(especialidade.toLowerCase())) {
                        totalPorEspecialidade[j] += salarioTotal;
                        break;
                    }
                }

            }
            return totalPorEspecialidade;
        }
//--------------------------------- organizar/sacar da tabela ---------------------------------//


//--------------------------------- algoritmo fdp do custo fixo ---------------------------------//
    public static double calcularSalarioFixo(double salarioBase, int anosServico) {
        int aumentos = anosServico / 5; // cada 5 anos tens aumentinho
        double salarioFixo = salarioBase;

        for (int i = 0; i < aumentos; i++) {
            salarioFixo *= 1.04; // aumento de 4%
        }

        return salarioFixo;
    }
//--------------------------------- algoritmo fdp do custo fixo ---------------------------------//


//--------------------------------- cenas para sacar a info das tabelas ---------------------------------//
    // sacar o salário base
    public static double obterSalarioBase(String especialidade, String[] especialidades) {
        for (String especialidadeInfo : especialidades) {
            String[] partes = especialidadeInfo.split("/");
            if (partes[0].equalsIgnoreCase(especialidade)) {
                return Double.parseDouble(partes[1]);
            }
        }
        return 0.0;
    }

    // sacar custo hora extra
    public static double obterCustoHoraExtra(String especialidade, String[] especialidades) {
        for (String especialidadeInfo : especialidades) {
            String[] partes = especialidadeInfo.split("/");
            if (partes[0].equalsIgnoreCase(especialidade)) {
                return Double.parseDouble(partes[2]);
            }
        }
        return 0.0;
    }

    //sacar nomes das especialidades
    public static String[] extrairNomesEspecialidades(String[] especialidades) {
        String[] nomes = new String[especialidades.length];
        for (int i = 0; i < especialidades.length; i++) {
            String[] partes = especialidades[i].split("/");
            nomes[i] = partes[0];
        }
        return nomes;
    }
//--------------------------------- cenas para sacar a info das tabelas ---------------------------------//
}
