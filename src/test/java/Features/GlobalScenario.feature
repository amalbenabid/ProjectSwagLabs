Feature: tester les différentes fonctionnalités sur le site Swag labs
  Background: tester les différentes fonctions
    Given je suis sur le site swag labs
    When je tape le username
    And je tape le passeword
    And je clique sur le bouton login
    Then je serais redigé vers la page home

    And je clique le menu liste pour sélectionner l'option
    And je clique sur le bouton add product

    Then je vérifie le panier si le produit est ajouté

@suppression @logout
Scenario: tester la suppression produit
    When je suis pointé sur la page home
    And je supprime l'article ajouté
    And  j'accède sur le panier
    Then vérifier l'existance du produit supprimé



@logout @suppression
    Scenario: tester le logout
    And je clique sur le bouton menu
    And  je clique sur le bouton logout
    Then je serais reg=dirig vers l'interface de connexion

  @checkout
  Scenario: tester le module checkout
    And je clique sur le bouton Chekout
    And je dois saisir firstname
    And je dois saisir lastname
    And je dois saisir le code postal
    And  je clique sur le bouton continuer
    Then  je clique sur le bouton finish et je serais redirigé vers la page chekoutComplet
    And je clique sur back home


