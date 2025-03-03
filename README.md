# MoviesDB
Este projeto é um aplicativo Android desenvolvido em Kotlin que consome a API do The Movie Database (TMDB) para exibir informações sobre filmes e séries.

🔑 Configuração da API TMDB

Crie uma conta no TMDB.

Obtenha sua chave de API.

No arquivo local.properties, adicione:

API_KEY=your_api_key_here

Adicione ao build.gradle.kts

🛠️ Tecnologias Utilizadas

Kotlin

Jetpack Compose (UI declarativa)

Retrofit (Requisições HTTP)

Koin (Injeção de dependências)

Coil (Carregamento de imagens)

📦 Estrutura do Projeto

app/
├── core/            # Constants, Extensions, Application...
├── data/            # Fonte de dados (API)
├── domain/          # Lógica de negócios e modelos
├── ui/              # Camada de apresentação (Jetpack Compose)
└── di/              # Injeção de dependência

