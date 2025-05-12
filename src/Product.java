import java.util.InputMismatchException;
import java.util.Scanner;


public class Product {

    private String tempName;
    private int tempQty;
    private double tempPrice;
    private int tempNumber;
    private boolean ativo = true; // Default value is true

    // Constructor with four parameters
    public Product(String tempName, int tempQty, double tempPrice, int tempNumber) {
        this.tempName = tempName;
        this.tempQty = tempQty;
        this.tempPrice = tempPrice;
        this.tempNumber = tempNumber;
    }

    // Getter for ativo
    public boolean isAtivo() {
        return ativo;
    }

    // Setter for ativo
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    // Método para calcular o valor do inventário
    public double getInventoryValue() {
        return tempPrice * tempQty;
    }

    @Override
    public String toString() {
        return "Nome: " + tempName +
                "\nPreço: " + tempPrice +
                "\nQuantidade: " + tempQty +
                "\nNumero do produto: " + tempNumber +
                "\nValor do Inventário: " + getInventoryValue() +
                "\nStatus do Produto: " + (ativo ? "ativo" : "descontinuado");
    }

    public static void getChoise(Scanner scanner, Product[] products) { 
         int stockChoise = -1; 
        while (stockChoise < 0 || stockChoise > 2) {
            try {
                System.out.println("Escolha o tipo de produto:");
                System.out.println("1. CD");
                System.out.println("2. DVD");
                System.out.println("0. Sair");
                System.out.print("Digite sua escolha: ");
                stockChoise = scanner.nextInt();
                scanner.nextLine(); // Limpa o buffer do scanner
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Digite um número inteiro.");
                scanner.nextLine(); // Limpa o buffer do scanner
            }       
            switch (stockChoise) {
                case 1:
                    // Adiciona produtos ao inventário
                    addCDToInventory(products, scanner);
                    break;
                case 2:
                    // Adiciona produtos ao inventário
                    addDVDToInventory(products, scanner);
                    break;
                case 0:
                    System.out.println("Saindo do programa...");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Somente os números de 0 a 2 são permitidos!");
                    break;
                    
            }
    }
    
}
    // metodo estatico para adicionar produtos ao inventario
    public static void addCDToInventory(Product[] products, Scanner scanner) {

        for (int i = 0; i < products.length; i++) {
            System.out.println("\nDigite os detalhes do produto " + (i + 1) + ":");
            System.out.print("Digite o nome do CD: ");
            String cdName = scanner.nextLine();
            System.out.print("Digite o nome do Artista: ");
            String artist = scanner.nextLine();
            System.out.print("Digite o selo de gravação: ");
            String recordSeal = scanner.nextLine();
            System.out.print("Digite o número de músicas: ");
            int numberOfSongs = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Digite a quantidade em Estoque: ");
            int qty = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Digite o preço do produto: ");
            double price = scanner.nextDouble();
            System.out.print("Digite o Numero do item: ");
            int number = scanner.nextInt();
            scanner.nextLine();
            // Criar e armazenar o produto no array
            products[i] = new Product.CD(cdName, qty, price, number, numberOfSongs, artist, recordSeal);
        }
    }

    // metodo estatico para adicionar produtos ao inventario
    public static void addDVDToInventory(Product[] products, Scanner scanner) {
        for (int i = 0; i < products.length; i++) {
        System.out.println("\nDigite os detalhes do DVD:");
        System.out.print("Nome do DVD: ");
        String dvdName = scanner.nextLine();
        System.out.print("Nome do estúdio cinematográfico: ");
        String filmStudio = scanner.nextLine();
        System.out.print("Classificação etária: ");
        String ageClassification = scanner.nextLine();
        System.out.print("Duração em minutos: ");
        int duration = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Quantidade em estoque: ");
        int qty = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Preço do produto: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Número do item: ");
        int number = scanner.nextInt();
        scanner.nextLine();
        // Criar e armazenar o produto no array
        products[i] = new Product.DVD(dvdName, qty, price, number, duration, ageClassification, filmStudio);
    }
}

    // Método para adicionar unidades ao estoque
    public void addToInventory(int quantidade) {
        if (!ativo) {
            System.out.println("Este produto foi descontinuado e não pode ter seu estoque alterado.");
            return; // Impede a alteração
        }
        if (quantidade > 0) {
            tempQty += quantidade;
            System.out.println("Foram adicionadas " + quantidade + " unidades ao estoque.");
        } else if (quantidade == 0) {
            tempQty = quantidade;
        } else {
            System.out.println("A quantidade deve ser um número positivo.");
        }
    }

    // Método para deduzir unidades do estoque
    public void deductFromInventory(int quantidade) {
        if (!ativo) {
            System.out.println("Este produto foi descontinuado e não pode ter seu estoque alterado.");
            return; // Impede a alteração
        }
        if (quantidade > 0 && quantidade <= tempQty) {
            tempQty -= quantidade;
            System.out.println("Foram removidas " + quantidade + " unidades do estoque.");
        } else {
            System.out.println("Não há estoque suficiente ou a quantidade inserida é inválida.");
        }
    }

    // Método para obter o número de produtos do usuário
    public static int getNumProducts(Scanner scanner) {
        int maxSize = -1;
        // Validar o número de produtos
        while (maxSize < 0) {
            try {
                System.out.print("Quantos produtos deseja adicionar? (0 para nenhum): ");
                maxSize = scanner.nextInt();

                if (maxSize < 0) {
                    System.out.println("Valor incorreto! O número de produtos deve ser positivo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Digite um número inteiro.");
                scanner.nextLine(); // Limpa o buffer do scanner
            }
        }
        scanner.nextLine(); // Limpa buffer
        return maxSize;
    }

    public static void getProductNumber(Product[] products) {
        System.out.println("\nLista de Produtos cadastrados:");

        for (int i = 0; i < products.length; i++) {
            if (products[i] != null) { // Garante que o produto foi inserido antes de exibir
                System.out.println("Produto " + i + ":" + " " + products[i].tempName);
            }
        }
    }

    public static void getMenuOption(Scanner scanner, Product[] products) {
        int escolha = -1;
        while (escolha < 0 || escolha > 4) {
            try {
                System.out.println("\nMENU PRINCIPAL");
                System.out.println("1. Exibir Inventário");
                System.out.println("2. Adicionar Estoque");
                System.out.println("3. Deduzir Estoque");
                System.out.println("4. Descontinuar Produto");
                System.out.println("0. Sair");
                System.out.print("Insira uma opção de menu: ");

                escolha = scanner.nextInt();
                scanner.nextLine();

                switch (escolha) {
                    case 1:
                        displayInventory(products);
                        break;
                    case 2:
                        getProductNumber(products);
                        System.out.print("Digite o número do produto que deseja adicionar estoque: ");
                        int produtoIndexAdd = scanner.nextShort();
                        System.out.print("Digite a quantidade a adicionar: ");
                        int quantidadeAdd = scanner.nextInt();
                        scanner.nextLine();

                        if (produtoIndexAdd >= 0 && produtoIndexAdd < products.length) {
                            products[produtoIndexAdd].addToInventory(quantidadeAdd);
                        } else {
                            System.out.println("Produto invalido.");
                        }
                        break;
                    case 3:
                        getProductNumber(products);
                        System.out.print("Digite o número do produto que deseja deduzir estoque: ");
                        int produtoIndexdeduct = scanner.nextInt();
                        System.out.print("Digite a quantidade a deduzir: ");
                        int quantidadededuct = scanner.nextInt();
                        scanner.nextLine();

                        if (produtoIndexdeduct >= 0 && produtoIndexdeduct < products.length) {
                            products[produtoIndexdeduct].deductFromInventory(quantidadededuct);
                        } else {
                            System.out.println("Produto inválido.");
                        }
                        break;
                    case 4:
                        getProductNumber(products);
                        System.out.print("Digite o número do produto que deseja descontinuar: ");
                        int produtoIndexDiscontinue = scanner.nextInt();
                        scanner.nextLine();

                        if (produtoIndexDiscontinue >= 0 && produtoIndexDiscontinue < products.length) {
                            products[produtoIndexDiscontinue].setAtivo(false);
                            System.out.println(
                                    "O produto \"" + products[produtoIndexDiscontinue].tempName
                                            + "\" foi descontinuado.");
                        } else {
                            System.out.println("Produto inválido.");
                        }
                        break;
                    case 0:
                        System.out.println("Encerrando o programa...");
                        scanner.close();
                        System.exit(0);
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! escolha entre 0 e 4 apenas!");
                scanner.nextLine(); // Consumir quebra de linha antes do próximo input
            }
        }
    }

    // metodo estatico para exibir o invetario de produtos
    public static void displayInventory(Product[] products) {
        if (products.length == 0) {
            System.out.println("\nInventário vazio!");
        } else {
            System.out.println("\nInventario de produtos: ");
            for (Product product : products) {
                if (product != null) {
                    System.out.println("\n" + product + "\n");
                }
            }
        }
    }

    public static class DVD extends Product {

        private int durationTime;
        private String ageClassification;
        private String filmStudio;

        // construtor chamando o Super() da classe Produto
        public DVD(String tempName, int tempQty, double tempPrice, int tempNumber, int durationTime,
                String ageClassification, String filmStudio) {
            super(tempName, tempQty, tempPrice, tempNumber);
            this.durationTime = durationTime;
            this.ageClassification = ageClassification;
            this.filmStudio = filmStudio;
        }

        // getters
        public int getdurationTime() {
            return durationTime;
        }

        public String getageClassification() {
            return ageClassification;
        }

        public String getfilmStudio() {
            return filmStudio;
        }

        // setters
        public void setdurationTime(int durationTime) {
            this.durationTime = durationTime;
        }

        public void setageClassification(String ageClassification) {
            this.ageClassification = ageClassification;
        }

        public void setfilmStudio(String filmStudio) {
            this.filmStudio = filmStudio;
        }

        @Override
        public double getInventoryValue() {
            return super.getInventoryValue() * 1.05;
        }

        @Override
        public String toString() {
            return super.toString() +
                   "\nDuração: " + durationTime +
                   "\nClassificação Etária: " + ageClassification +
                   "\nEstúdio de Cinema: " + filmStudio;
        }

    }

    public static class CD extends Product {

        private int songsAlbum;
        private String artist;
        private String recordSeal;

        // construtor chamando o Super() da classe Produto
        public CD(String tempName, int tempQty, double tempPrice, int tempNumber, int songsAlbum, String artist,
                String recordSeal) {
            super(tempName, tempQty, tempPrice, tempNumber);
            this.songsAlbum = songsAlbum;
            this.artist = artist;
            this.recordSeal = recordSeal;
        }

        // getters
        public int getsongAlgum() {
            return songsAlbum;
        }

        public String getartist() {
            return artist;
        }

        public String getrecordSeal() {
            return recordSeal;
        }

        // setters
        public void setsongAlbum(int songsAlbum) {
            this.songsAlbum = songsAlbum;
        }

        public void setartist(String artist) {
            this.artist = artist;
        }

        public void setrecordSeal(String recordSeal) {
            this.recordSeal = recordSeal;
        }

        @Override
        public double getInventoryValue() {
            return super.getInventoryValue() * 1.05;
        }

        @Override
        public String toString() {
            return super.toString() +
                   "\nArtista: " + artist +
                   "\nNúmero de músicas: " + songsAlbum +
                   "\nSelo de Gravação: " + recordSeal;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Obter o número de produtos chamando getNumProducts
        int maxSize = getNumProducts(scanner);
        // Criar array para armazenar produtos
        Product[] products = new Product[maxSize];
        // chama os metodos de adicionar produtos
        getChoise(scanner, products);
        // chamada do método getMenuOption
        while (true)
            getMenuOption(scanner, products);
    }
}
