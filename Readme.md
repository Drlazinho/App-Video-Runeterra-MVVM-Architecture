#  Arquitetura MVVM no Android com Kotlin

### MVVM = Model-View-ViewModel

A arquitetura MVVM remove o forte acoplamento entre cada componente

Mais ainda, nesta arquitetura, os filhos não tem uma referência direta ao pai, eles só tem a referencia por observáveis (observables).

Isso facilita a criação de testes para a sua aplicação, coisa que beira o impossível com arquiteturas de componentes fortemente acoplados.

#### Model:

* É a camada que contem os dados do aplicativo.
* Ela **NÃO PODE** se comunicar diretamente com a View. Geralmente, é recomendado expor os dados ao ViewModel por meio de Observables.

#### View

* Ela representa a IU do aplicativo, e deve ser desprovida de qualquer lógica de negócio. Ela observa o ViewModel por meio dos Observables.

#### ViewModel

* Essa camada atua como um link entre a Model e a View.
* É ela quem é responsável por transformar os dados do modelo.
* Ela fornece fluxos de dados para a View.
* Ela tambem usa hooks ou retornos de chamada para atualizar a View.

O gradle é o responsavel por buildar a aplicação

**Importante**

* Adiciona o plugin kotlin.kapt - para adicionar dependencias

## :mobile_phone_off: Sobre o App

A aplicação foi desenvolvida com objetivo de aplicar os conceitos de MVVM. O aplicativo faz requesição de uma [API simples, fake,](https://raw.githubusercontent.com/Drlazinho/video-api/main/video.json) que retorna uma lista de vídeos sobre Legends of Runeterra. Foi utilizado o Retrofit

#### Criando a ViewModel

* Na configuração da classe ViewModel, precisamos criar uma classe e estender o ViewModel.
* É na classe ViewModel que fica a lógica de negócios e implementações de chamdas de API.
* No construtor ViewModel, precisamos passar o repositório de dados que criamos no passo anterior para lidar com os dados.
* **Importante:** Estamos usando dados Videos para atualizar os dados para a IU (Interface de usuário).
* Como o LiveData respeita o ciclo de vida do Android, isso significa que ele não invocará seu retorno de chamada do observador, a menos que a activity ou fragment tenha passado pelo onStart(), e não tenha passada pelo onStop ().
* Além disso o LiveData também removerá automaticamente o observador quando seu host receber onDestroy().
* Para entender os conceitos de Ciclo de Vida do Android;

#### Criando a ViewModelFactory (Design Pattern)

* Não podemos criar ViewModel por conta propia. Precisamos do utilitário ViewModelProviders fornecido pelo Android para criar ViewModels.
* **PONTO DE ATENÇÃO**: ViewModelProviders só pode instanciar ViewModels sem nenhum construtor ou argumentos.
* Portanto, se eu tiver um ViewModel com vários argumentos, preciso usar um Factory que possa passar para ViewModelProviders, para usar quando uma instância de MainViewModel for necessária.

### Resultado

<img src="resultado-app-mvvm.jpg" width="40%"/>





