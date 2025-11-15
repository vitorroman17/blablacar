import domain.entities.Usuario;
import domain.entities.Veiculo;
import domain.entities.Viagem;

import java.util.List;
import java.util.Scanner;

import repository.UsuarioRepository;
import repository.ViagemRepository;

public class App {

    private static final Scanner scanner = new Scanner(System.in);
    private static Usuario usuarioLogado = null;
    private static Viagem viagemAtual = null;
    private static Veiculo veiculoAcionado = null;
    private static final UsuarioRepository usuarioRepo = new UsuarioRepository();
    private static final ViagemRepository viagemRepo = new ViagemRepository();

    public static void main(String[] args) {

        exibirBanner();

        while (true) {
            if (usuarioLogado == null) {
                menuInicial();
            } else {
                menuPrincipal();
            }
        }
    }

    private static void exibirBanner() {
        System.out.println("\n╔═══════════════════════════════════════════════════╗");
        System.out.println("║                                                   ║");
        System.out.println("║             SISTEMA DE CARONAS - CaronaApp        ║");
        System.out.println("║                                                   ║");
        System.out.println("║          Compartilhe viagens, economize!          ║");
        System.out.println("║                                                   ║");
        System.out.println("╚═══════════════════════════════════════════════════╝\n");
    }

    private static void menuInicial() {
        System.out.println("\n┌─────────────────────────────────────┐");
        System.out.println("│         MENU INICIAL                │");
        System.out.println("└─────────────────────────────────────┘");
        System.out.println("│ 1. Fazer Login                      │");
        System.out.println("│ 2. Criar Conta                      │");
        System.out.println("│ 3. Buscar Caronas (Visitante)       │");
        System.out.println("│ 0. Sair                             │");
        System.out.println("└─────────────────────────────────────┘");
        System.out.print("\nEscolha uma opção: ");

        int opcao = scanner.nextInt();

        switch (opcao) {
            case 1 -> fazerLogin();
            case 2 -> criarConta();
            case 3 -> buscarCaronasVisitante();
            case 0 -> sair();
            default -> System.out.println("\n❌ Opção inválida! Tente novamente.");
        }
    }

    private static void menuPrincipal() {
        System.out.println("\n╔═══════════════════════════════════════════════════╗");
        System.out.println("║  Bem-vindo(a), " + usuarioLogado.getNome() + "! ");
        System.out.println("╚═══════════════════════════════════════════════════╝");

        System.out.println("\n┌─────────────────────────────────────┐");
        System.out.println("│         MENU PRINCIPAL              │");
        System.out.println("├─────────────────────────────────────┤");
        System.out.println("│   CARONAS                           │");
        System.out.println("│       1. Ofertar Nova Carona        │");
        System.out.println("│       2. Buscar Caronas Disponíveis │");
        System.out.println("│       3. Minhas Caronas Ofertadas   │");
        System.out.println("│       4. Minhas Reservas            │");
        System.out.println("│                                     │");
        System.out.println("│   VEÍCULOS                          │");
        System.out.println("│       5. Cadastrar Veículo          │");
        System.out.println("│       6. Meus Veículos              │");
        System.out.println("│                                     │");
        System.out.println("│   PERFIL                            │");
        System.out.println("│       7. Meu Perfil                 │");
        System.out.println("│                                     │");
        System.out.println("│   RELATÓRIOS                        │");
        System.out.println("│       9. Histórico de Caronas       │");
        System.out.println("│       10. Relatório Completo        │");
        System.out.println("│                                     │");
        System.out.println("│   11. Fazer Logout                  │");
        System.out.println("│   0. Sair do Sistema                │");
        System.out.println("└─────────────────────────────────────┘");
        System.out.print("\nEscolha uma opção: ");

        int opcao = scanner.nextInt();

        switch (opcao) {
            case 1 -> ofertarCarona();
            case 2 -> buscarCaronas();
            case 3 -> minhasCaronasOfertadas();
            case 4 -> minhasReservas();
            case 5 -> novoVeiculo();
            case 6 -> meusVeiculos();
            case 7 -> meuPerfil();
            case 9 -> historicoCaronas();
            case 10 -> relatorioCompleto();
            case 11 -> fazerLogout();
            case 0 -> sair();
            default -> System.out.println("\n Opção inválida! Tente novamente.");
        }
    }

    private static void fazerLogin() {
        System.out.println("\n┌─────────────────────────────────────┐");
        System.out.println("│           FAZER LOGIN               │");
        System.out.println("└─────────────────────────────────────┘");
        scanner.nextLine(); // limpa \n do menu
        System.out.print("E-mail: ");
        String email = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        usuarioLogado = usuarioRepo.Login(email, senha);
        if (usuarioLogado != null) {
            System.out.println("\n Login realizado com sucesso!");
            pausar();
        } else {
            System.out.println("\n E-mail ou senha incorretos!");
            pausar();
        }
    }

    private static void criarConta() {
        System.out.println("\n┌─────────────────────────────────────┐");
        System.out.println("│          CRIAR NOVA CONTA           │");
        System.out.println("└─────────────────────────────────────┘");
        scanner.nextLine(); // limpa \n do menu
        System.out.print("Nome completo: ");
        String nome = scanner.nextLine();

        System.out.print("E-mail: ");
        String email = scanner.nextLine();

        System.out.print("Telefone (com DDD): ");
        String telefone = scanner.nextLine();

        System.out.print("Qual o seu endereço? ");
        String endereco = scanner.nextLine();

        System.out.print("Senha (mínimo 6 caracteres): ");
        String senha = scanner.nextLine();

        try {
            usuarioLogado = new Usuario(nome, email, senha, telefone, endereco);
            usuarioRepo.addUsuario(usuarioLogado);
            usuarioRepo.getUsuarios();
        } catch (Exception e) {
            usuarioLogado = null;
        }

        if (usuarioLogado != null) {
            System.out.println("\n Conta criada com sucesso!");
            pausar();
        } else {
            System.out.println("\n Falha ao criar conta. Tente novamente.");
            pausar();
        }
    }

    private static void fazerLogout() {
        System.out.println("\n👋 Até logo, " + usuarioLogado.getNome() + "!");
        usuarioLogado = null;
        viagemAtual = null;
        veiculoAcionado = null;
    }

    private static void ofertarCarona() {
        // chamado logo após um nextInt do menu
        scanner.nextLine(); // limpa \n pendente

        if (!verificarMotorista()) {
            return;
        }
        if (!verificarVeiculo()) { // garante que existe pelo menos um veículo
            return;
        }

        System.out.println("\n┌─────────────────────────────────────┐");
        System.out.println("│        OFERTAR NOVA CARONA          │");
        System.out.println("└─────────────────────────────────────┘");

        System.out.println("\n📍 ORIGEM");
        System.out.print("Cidade de origem: ");
        String cidadeOrigem = scanner.nextLine();

        System.out.println("\n📍 DESTINO");
        System.out.print("Cidade de destino: ");
        String cidadeDestino = scanner.nextLine();

        System.out.println("\n🕐 Data");
        System.out.print("Data da viagem (dd/MM/yyyy): ");
        String data = scanner.nextLine();

        System.out.println("\n🚗 Selecione o veículo da viagem:");
        mostrarVeiculos();
        System.out.print("Informe o ID do veículo: ");
        int idVeiculo = scanner.nextInt();
        scanner.nextLine(); // limpa \n

        Veiculo veiculoSelecionado = null;
        for (Veiculo v : usuarioLogado.getMotorista().getVeiculos()) {
            if (v.getId() == idVeiculo) {
                veiculoSelecionado = v;
                break;
            }
        }

        if (veiculoSelecionado == null) {
            System.out.println("\n❌ Veículo não encontrado. Carona não criada.");
            pausar();
            return;
        }

        System.out.println("\n💺 VAGAS E VALOR");
        System.out.print("Número de vagas disponíveis: ");
        int vagas = scanner.nextInt();

        System.out.print("Valor por pessoa (R$): ");
        double valor = scanner.nextDouble();

        // cria a viagem com o veículo correto
        viagemAtual = new Viagem(veiculoSelecionado, usuarioLogado, cidadeOrigem, cidadeDestino, valor, data, vagas);
        viagemRepo.addViagem(viagemAtual);

        System.out.println("\n✅ Carona ofertada com sucesso!");
        System.out.println("📋 Resumo:");
        System.out.println("   De: " + cidadeOrigem + " → Para: " + cidadeDestino);
        System.out.println("   Data: " + data);
        System.out.println("   Veículo: " + veiculoSelecionado.getMarca() + " "
                + veiculoSelecionado.getModelo() + " (" + veiculoSelecionado.getAno() + ")");
        System.out.println("   Vagas: " + vagas + " | Valor: R$ " + String.format("%.2f", valor));
    }

    private static void buscarCaronas() {
        System.out.println("\n┌─────────────────────────────────────┐");
        System.out.println("│      BUSCAR CARONAS DISPONÍVEIS     │");
        System.out.println("└─────────────────────────────────────┘");
        scanner.nextLine(); // limpa \n do menu

        System.out.print("Cidade de origem: ");
        String origem = scanner.nextLine();

        System.out.print("Cidade de destino: ");
        String destino = scanner.nextLine();

        System.out.print("Data (dd/MM/yyyy) [Enter para qualquer data]: ");
        String data = scanner.nextLine();

        List<Viagem> resultados = viagemRepo.ListarViagensPorOrigemDestino(origem, destino, data);

        System.out.println("\n📋 CARONAS ENCONTRADAS:");
        System.out.println("─────────────────────────────────────────────────────");

        if (resultados.isEmpty()) {
            System.out.println("\nNenhuma carona encontrada para os filtros informados.");
            pausar();
            return;
        }

        for (Viagem v : resultados) {
            System.out.println("\n ID: " + v.getId());
            System.out.println("   De: " + v.getCidadeOrigem() + " → Para: " + v.getCidadeDestino());
            System.out.println("   Motorista: " + v.getMotorista().getNome());
            System.out.println("   Veículo:");
            System.out.println("      Marca:  " + v.getVeiculo().getMarca());
            System.out.println("      Modelo: " + v.getVeiculo().getModelo());
            System.out.println("      Ano:    " + v.getVeiculo().getAno());
            System.out.println("      Cor:    " + v.getVeiculo().getCor());
            System.out.println("  Saída: " + v.getData());
            System.out.println("  Vagas disponíveis: " + v.getVagas());
            System.out.printf("  Valor: R$ %.2f por pessoa%n", v.getPreco());
        }

        System.out.println("\n─────────────────────────────────────────────────────");
        System.out.print("\nDeseja reservar alguma carona? (ID da viagem ou 0 para voltar): ");
        int escolha = scanner.nextInt();
        scanner.nextLine(); // limpa \n

        if (escolha > 0) {
            if (verificarPassageiro()) {
                reservarCarona(escolha);
            }
        }
    }

    private static void reservarCarona(int idViagem) {
        System.out.println("\n┌─────────────────────────────────────┐");
        System.out.println("│         RESERVAR CARONA             │");
        System.out.println("└─────────────────────────────────────┘");

        System.out.print("Quantas pessoas (incluindo você)? ");
        int pessoas = scanner.nextInt();

        viagemAtual = viagemRepo.reservarViagemPorId(idViagem, pessoas, usuarioLogado);

        if (viagemAtual == null) {
            System.out.println("\n❌ Não foi possível reservar a carona (viagem não encontrada ou sem vagas).");
            return;
        }

        System.out.println("Sua carona para " + viagemAtual.getCidadeDestino() + " foi reservada!");
        System.out.println("O motorista receberá sua solicitação.");
        System.out.println("Você pode acompanhar em 'Minhas Reservas'.");
    }

    private static void minhasCaronasOfertadas() {
        System.out.println("\n┌─────────────────────────────────────┐");
        System.out.println("│      MINHAS CARONAS OFERTADAS       │");
        System.out.println("└─────────────────────────────────────┘");
        scanner.nextLine(); 

        
        System.out.println("\n📋 Caronas Ativas:");
        System.out.println("─────────────────────────────────────────────────────");
        System.out.println("\n1. Florianópolis → Curitiba");
        System.out.println("   🕐 25/11/2024 às 08:00");
        System.out.println("   💺 Vagas: 2/4 ocupadas");
        System.out.println("   💰 R$ 80,00 por pessoa");
        System.out.println("   📊 Status: DISPONÍVEL");

        System.out.println("\n─────────────────────────────────────────────────────");
        System.out.println("\nOpções:");
        System.out.println("1. Ver detalhes de uma carona");
        System.out.println("2. Cancelar uma carona");
        System.out.println("3. Iniciar viagem");
        System.out.println("4. Concluir viagem");
        System.out.println("0. Voltar");

        System.out.print("\nEscolha: ");
        int opcao = scanner.nextInt();

        if (opcao > 0 && opcao <= 4) {
            System.out.println("\n Ação realizada com sucesso!");
            scanner.nextLine();
            pausar();
        }
    }

    private static void minhasReservas() {
        System.out.println("\n┌─────────────────────────────────────┐");
        System.out.println("│          MINHAS RESERVAS            │");
        System.out.println("└─────────────────────────────────────┘");
        scanner.nextLine();

        List<Viagem> reservas = viagemRepo.ListarViagensPorPassageiro(usuarioLogado);

        if (reservas.isEmpty()) {
            System.out.println("\nVocê não possui reservas.");
            pausar();
            return;
        }

        System.out.println("\n Reservas:");
        for (Viagem v : reservas) {
            System.out.println("─────────────────────────────────────────────────────");
            System.out.println("\n Id: " + v.getId());
            System.out.println("De " + v.getCidadeOrigem() + " para " + v.getCidadeDestino());
            System.out.println("  Motorista: " + v.getMotorista().getNome());
            System.out.println("  Data: " + v.getData());
            System.out.println("  Pessoas reservadas: " + v.getPassageiros().size());
            System.out.println("  Total: R$ " + String.format("%.2f", v.getPreco() * v.getPassageiros().size()));
            System.out.println("  Status: CONFIRMADA");
        }

        System.out.println("\n─────────────────────────────────────────────────────");
        System.out.print("Selecione uma reserva (ID da viagem): ");
        int idViagem = scanner.nextInt();

        System.out.println("\n─────────────────────────────────────────────────────");
        System.out.println("\nOpções:");
        System.out.println("1. Ver detalhes");
        System.out.println("2. Cancelar reserva");
        System.out.println("3. Avaliar carona (após conclusão)");
        System.out.println("0. Voltar");

        System.out.print("\nEscolha: ");
        int opcao = scanner.nextInt();

        if (opcao == 2) {
            System.out.print("Quantas pessoas deseja cancelar? ");
            int pessoas = scanner.nextInt();
            scanner.nextLine(); // limpa \n
            System.out.print("Tem certeza que deseja cancelar esta reserva? (S/N): ");
            String resposta = scanner.nextLine();
            if (resposta.equalsIgnoreCase("S")) {
                viagemRepo.cancelarReserva(idViagem, usuarioLogado, pessoas);
                System.out.println("\n Reserva cancelada!");
                pausar();
            }
        } else if (opcao == 3) {
            avaliarCarona(idViagem);
        }
    }

    private static void avaliarCarona(int idViagem) {
        System.out.println("\n┌─────────────────────────────────────┐");
        System.out.println("│         AVALIAR CARONA              │");
        System.out.println("└─────────────────────────────────────┘");

        System.out.print("\nNota de 1 a 5: ");
        int nota = scanner.nextInt();

        if (nota < 1 || nota > 5) {
            System.out.println("\n Nota inválida!");
            scanner.nextLine(); // limpa \n
            pausar();
            return;
        }

        System.out.println("\nAvaliação enviada com sucesso!");
        System.out.println(viagemRepo.atualizarAvaliacaoViagem(nota, idViagem));
    }

    private static void novoVeiculo() {
        scanner.nextLine(); // limpa \n do menu

        if (verificarMotorista()) {
            System.out.println("\n┌─────────────────────────────────────┐");
            System.out.println("│       CADASTRAR NOVO VEÍCULO        │");
            System.out.println("└─────────────────────────────────────┘");

            veiculoAcionado = cadastrarVeiculo();

            if (veiculoAcionado != null) {
                System.out.println("\n✅ Veículo cadastrado com sucesso!");
                System.out.println("Veiculo: Cor: " + veiculoAcionado.getCor() + " "
                        + veiculoAcionado.getMarca() + " " + veiculoAcionado.getModelo()
                        + " (" + veiculoAcionado.getAno() + ")");
                System.out.println("📋 Placa: " + veiculoAcionado.getPlaca());
            }
        }
    }

    private static void meusVeiculos() {
        System.out.println("\n┌─────────────────────────────────────┐");
        System.out.println("│          MEUS VEÍCULOS              │");
        System.out.println("└─────────────────────────────────────┘");
        scanner.nextLine(); // limpa \n do menu

        if (usuarioLogado.getMotorista() == null ||
                usuarioLogado.getMotorista().getVeiculos().isEmpty()) {
            System.out.println("\nVocê não possui veículos cadastrados.");
            pausar();
            return;
        }

        System.out.println("\n🚗 Veículos Cadastrados:");
        System.out.println("─────────────────────────────────────────────────────");

        mostrarVeiculos();
        pausar();
    }

    private static void mostrarVeiculos() {
        for (Veiculo veiculo : usuarioLogado.getMotorista().getVeiculos()) {
            System.out.println("\nID: " + veiculo.getId());
            System.out.println("Marca: " + veiculo.getMarca());
            System.out.println("Modelo: " + veiculo.getModelo());
            System.out.println("Ano: " + veiculo.getAno());
            System.out.println("Cor: " + veiculo.getCor());
            System.out.println("Placa: " + veiculo.getPlaca());
            System.out.println("\n═════════════════════════════════════════════════════");
        }
    }

    private static void meuPerfil() {
        System.out.println("\n┌─────────────────────────────────────┐");
        System.out.println("│            MEU PERFIL               │");
        System.out.println("└─────────────────────────────────────┘");

        System.out.println("\nUsuário: " + usuarioLogado.getNome());
        System.out.println("Email: " + usuarioLogado.getEmail());
        System.out.println("Telefone: " + usuarioLogado.getTelefone());
        System.out.println(" Avaliação: (implementação pendente)");
        System.out.println("\n Estatísticas:");
        System.out.println(" Caronas oferecidas: (implementação pendente)");
        System.out.println(" Caronas como passageiro: (implementação pendente)");
        System.out.println(" Caronas concluídas: (implementação pendente)");
    }

    private static void historicoCaronas() {
        System.out.println("\n┌─────────────────────────────────────┐");
        System.out.println("│       HISTÓRICO DE CARONAS          │");
        System.out.println("└─────────────────────────────────────┘");

        System.out.println("─────────────────────────────────────────────────────");

        System.out.println("\n 15/11/2024 - Florianópolis → Curitiba");
        System.out.println("   Tipo: Motorista | Status: CONCLUÍDA");
        System.out.println("   Passageiros: 3 | Ganhos: R$ 240,00");

        System.out.println("\n 05/11/2024 - Rio → Petrópolis");
        System.out.println("   Tipo: Motorista | Status: CANCELADA");
    }

    private static void relatorioCompleto() {
        System.out.println("\n┌─────────────────────────────────────┐");
        System.out.println("│        RELATÓRIO COMPLETO           │");
        System.out.println("└─────────────────────────────────────┘");
        scanner.nextLine();

        System.out.println("\n📊 RESUMO GERAL - " + usuarioLogado.getNome());
        System.out.println("═════════════════════════════════════════════════════");
        
        if (!(usuarioLogado.getMotorista() == null)) {
            System.out.println("\n🚗 COMO MOTORISTA:");
            System.out.printf("   Total de caronas oferecidas: %d", usuarioLogado.getMotorista().getNumeroDeViagens());
            int contagemViagens;
            contagemViagens = listarViagemMotorista("CONCLUIDA");
            System.out.printf("   Caronas concluídas: " + contagemViagens);

            contagemViagens = listarViagemMotorista("CANCELADA");
            System.out.println("   Caronas canceladas: " + contagemViagens);

            contagemViagens = viagemRepo.contarPassageirosPorMotorista(usuarioLogado);
            System.out.println("   Total de passageiros transportados: " + contagemViagens);
            double receitaTotal = viagemRepo.calcularReceitaTotalPorMotorista(usuarioLogado);
            System.out.println("   Receita total: R$ " + receitaTotal);
        }
        
        if(!(usuarioLogado.getPassageiro() == null)) {
        System.out.println("\n💺 COMO PASSAGEIRO:");
        System.out.println("   Total de caronas: 8");
        System.out.println("   Caronas concluídas: 7");
        System.out.println("   Caronas canceladas: 1");
        System.out.println("   Total gasto: R$ 420,00");
        System.out.println("   Avaliação média: ⭐ 4.6");
        }
        System.out.println("\n🎯 DESTINOS MAIS FREQUENTES:");
        System.out.println("   1. Curitiba (5 viagens)");
        System.out.println("   2. São Paulo (4 viagens)");
        System.out.println("   3. Rio de Janeiro (3 viagens)");

        System.out.println("\n💰 ECONOMIA/GANHOS:");
        System.out.println("   Economia em combustível: R$ 630,00");
        System.out.println("   Balanço final: +R$ 1.430,00");

        System.out.println("\n═════════════════════════════════════════════════════");
        System.out.print("\nDeseja exportar este relatório? (S/N): ");
        String exportar = scanner.nextLine().trim().toUpperCase();

        if (exportar.equals("S")) {
            System.out.println("\n✅ Relatório exportado para: relatorio_" + usuarioLogado.getNome() + ".txt");
        }
    }

    private static void buscarCaronasVisitante() {
        System.out.println("\n┌─────────────────────────────────────┐");
        System.out.println("│     BUSCAR CARONAS (VISITANTE)      │");
        System.out.println("└─────────────────────────────────────┘");
        System.out.println("\n Faça login ou crie uma conta para reservar caronas!");
        scanner.nextLine();

        System.out.print("\nCidade de origem: ");
        String origem = scanner.nextLine();

        System.out.print("Cidade de destino: ");
        String destino = scanner.nextLine();

        System.out.println("\n CARONAS DISPONÍVEIS:");
        System.out.println("─────────────────────────────────────────────────────");
        System.out.println("\nEncontramos 5 caronas de " + origem + " para " + destino);
        System.out.println("\n🔒 Crie uma conta para ver detalhes e reservar!");
    }

    private static void pausar() {
        System.out.print("\nPressione ENTER para continuar...");
        scanner.nextLine();
    }

    private static void sair() {
        System.out.println("\n╔═══════════════════════════════════════════════════╗");
        System.out.println("║        Obrigado por usar o CaronaApp! 🚗         ║");
        System.out.println("╚═══════════════════════════════════════════════════╝\n");
        System.exit(0);
    }

    private static boolean verificarMotorista() {
        if (usuarioLogado.getMotorista() != null) {
            return true;
        }

        System.out.println("\n Apenas motoristas podem ofertar caronas!");
        System.out.print("Gostaria de se cadastrar como motorista para oferecer caronas? (S/N): ");
        String resposta = scanner.nextLine();

        if (resposta.equalsIgnoreCase("S")) {
            System.out.print("Por favor, forneça sua CNH para completar o cadastro: ");
            String cnh = scanner.nextLine();
            usuarioLogado.setMotorista(cnh);

            if (usuarioLogado.getMotorista() != null) {
                System.out.println("\n✅ Cadastro como motorista realizado com sucesso!");
                return true;
            }
        }
        return false;
    }

    private static boolean verificarPassageiro() {
        if (usuarioLogado.getPassageiro() != null) {
            return true;
        }

        System.out.println("\n Apenas passageiros podem reservar caronas!");
        System.out.print("Gostaria de se cadastrar como passageiro para utilizar caronas? (S/N): ");
        String resposta = scanner.nextLine();

        if (resposta.equalsIgnoreCase("S")) {
            usuarioLogado.setPassageiro();
            if (usuarioLogado.getPassageiro() != null) {
                System.out.println("\n Cadastro como passageiro realizado com sucesso!");
                return true;
            }
        }
        return false;
    }

    public static boolean verificarVeiculo() {
        if (verificarMotorista()) {
            return true;
        }if (!usuarioLogado.getMotorista().getVeiculos().isEmpty()) {
            return true;
        }

        scanner.nextLine();
        System.out.println("\nVocê ainda não tem nenhum veículo cadastrado!");
        System.out.print("Gostaria de cadastrar um veículo agora? (S/N): ");
        String resposta = scanner.nextLine();

        if (resposta.equalsIgnoreCase("S")) {
            veiculoAcionado = cadastrarVeiculo();
            return veiculoAcionado != null;
        }
        return false;
    }

    public static Veiculo cadastrarVeiculo() {
        System.out.print("Marca: ");
        String marca = scanner.nextLine();

        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();

        System.out.print("Ano: ");
        int ano = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Cor: ");
        String cor = scanner.nextLine();

        System.out.print("Placa (ABC1234): ");
        String placa = scanner.nextLine().toUpperCase();

        veiculoAcionado = new Veiculo(marca, modelo, placa, ano, cor);

        usuarioLogado.getMotorista().addVeiculo(veiculoAcionado);

        return veiculoAcionado;
    }
    public static int  listarViagemMotorista(String status) {
        int contagemViagens = 0;
        for(Viagem v : viagemRepo.ListarViagensPorMotorista(usuarioLogado)) {
            if(v.getStatus().equals(status)) {
                contagemViagens++;
            }
        }
        return contagemViagens;
    }
}
