import java.util.Scanner;

public class AppSaude {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printCaixinha("Centro Hospitalar de Coimbra");

        int numEsp = ValorPositivo("Quantas Especialidades Deseja Inserir? ", scanner); //tem de ser positivo
        String[] especialidades = new String[numEsp]; //array p meter la cada especialidade criada

        for (int i = 0; i < numEsp; i++) {
            printCaixinha("Menu Inserir Especialidade " + (i + 1));

            System.out.print("Especialidade: ");
            String nomeEspecialidade = scanner.nextLine(); //especialidade commo string naturalmente
            double salarioBase = ValorPositivo("Salário Base: ", scanner);
            double custoHoraExtra = ValorPositivo("Custo Hora Extra: ", scanner);

            especialidades[i] = nomeEspecialidade + "/" + salarioBase + "/" + custoHoraExtra; //formato /_/_/
        }

        // agora vamos aos medicos
        int numMedicos = ValorPositivo("Quantos Médicos Deseja Inserir? ", scanner);
        String[] medicos = new String[numMedicos]; //array para meter la cada medico criado

        for (int i = 0; i < numMedicos; i++) {
            printCaixinha("Inscrição do Médico " + (i + 1));

            System.out.print("Nome Médico: ");
            String nomeMedico = scanner.nextLine();
            String especialidade = obterEspecialidade(especialidades, scanner);
            int anosServico = ValorPositivo("Anos serviço: ", scanner);
            int horasExtra = ValorPositivo("Horas extra: ", scanner);

            medicos[i] = nomeMedico + " / " + especialidade + " / " + anosServico + " / " + horasExtra;
        }

        //salarios
        for (int i = 0; i < medicos.length; i++) {
            String infoMedico = medicos[i];
            String[] dadosDoMedico = infoMedico.split("/");

            String nome = dadosDoMedico[0]; //nome
            String especialidade = dadosDoMedico[1]; //especialidade
            int anosServico = Integer.parseInt(dadosDoMedico[2]); //jahren von service
            int horasExtra = Integer.parseInt(dadosDoMedico[3]); //extra stunden digga

            // nos dados da especialidade
            int qualEspecialidade = encontrarEspecialidade(especialidades, especialidade);
            if (qualEspecialidade != -1) {
                String[] especialidadeDados = especialidades[qualEspecialidade].split("/"); //se encontrar, sera o indice da especialidade epsecifica que queremos
                double salarioBase = Double.parseDouble(especialidadeDados[1]); //depois vem o salario base dessa especialidad
                double custoHoraExtra = Double.parseDouble(especialidadeDados[2]); //e o custo da horita
            }
        }

        // print salarios e especialidade e etc...

        scanner.close();
    }








    //--------------------------------------- funcoes ---------------------------------------//


    // funcao de ser valores sempre positivos no input
    public static int ValorPositivo(String inputMensagem, Scanner scanner) {
        int numero = -1; //tp boolean
        while (numero < 0) {
            try {
                System.out.print(inputMensagem);
                numero = Integer.parseInt(scanner.nextLine()); //temos de converter p int
                if (numero < 0) {
                    System.out.println("Erro!! Insira um Valor Positivo: ");
                }
            } catch (NumberFormatException e) { //se nem sequer for numero
                System.out.println("Erro!! Insira um Balor Numérico: ");
            }
        }
        return numero;
    }





    // funcao p ver se especialidade existe... converter p lower case so p n haver duvidas
    public static int encontrarEspecialidade(String[] especialidades, String nomeEspecialidade) {
        String nomeEspecialidadeLower = nomeEspecialidade.toLowerCase();
        for (int i = 0; i < especialidades.length; i++) {
            String[] especialidadeDados = especialidades[i].split("/");
            if (especialidadeDados[0].toLowerCase().equals(nomeEspecialidadeLower)) { //portanto se o 1o valor, ou seja, o nome da esp for igual ao que queremos comparar
                return i; // return desse saucy indice
            }
        }
        return -1; //-1 se n encontrar
    }





    // ver se estamos a inserir uma especi que ja existe
    public static String obterEspecialidade(String[] especialidades, Scanner scanner) {
        String especialidade;
        while (true) {
            System.out.print("Insira a Especialidade (Existentes: ");
            for (int j = 0; j < especialidades.length; j++) {
                System.out.print(especialidades[j].split("/")[0]); //p dar print so dos nomes de cada especialidade - indice 0
                if (j < especialidades.length - 1) { //enquanto houver mais especialidade, meter " // " para ver a proxima
                    System.out.print(" // ");
                }
            }
            System.out.print("): "); //quando acabarem todas esp, fechamos


            especialidade = scanner.nextLine();
            // verificar
            if (encontrarEspecialidade(especialidades, especialidade) != -1) {
                return especialidade;
            } else {
                System.out.println("Erro!! Especialidade inválida. Insira novamente.");
            }
        }
    }




    // caixinha bonita nos prints
    public static void printCaixinha(String texto) {
        int comprimento = texto.length();
        String contorno = "";
        for (int i = 0; i < comprimento + 4; i++) {
            contorno += "/";
        }

        System.out.println(contorno);
        System.out.println("/ " + texto + " /");
        System.out.println(contorno);
    }

}