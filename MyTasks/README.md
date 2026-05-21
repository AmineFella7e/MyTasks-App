# MyTasks - Projet Android Studio Java

Application simple de gestion de taches realisee en **Java** avec **Android Studio**.

## Fonctionnalites

- Ecran de connexion simple avec passage du nom entre activites
- Ajout d'une tache via un formulaire
- Affichage des taches dans un `RecyclerView`
- Detail, modification et suppression d'une tache
- Menu d'application avec actions rapides
- Boites de dialogue de confirmation (`AlertDialog`)
- Fragment d'information avec bouton et dialogue
- Base de donnees locale **Realm** (CRUD)
- Petit plus : ouverture du composeur telephonique avec `ACTION_DIAL`
- Petit plus : choix de date avec `DatePickerDialog`

## Correspondance avec les TP

- **TP2** : layouts XML, `EditText`, `Spinner`, `RatingBar`, `Button`
- **TP3** : plusieurs activites, `Intent`, `putExtra/getIntent`, navigation
- **TP4** : menu, `Toast`, `Fragment`, `AlertDialog`
- **TP5** : liste avec `RecyclerView` + adaptateur personnalise
- **TP6** : base de donnees **Realm** avec ajout, lecture, modification, suppression

## Structure

- `LoginActivity` : ecran d'entree utilisateur
- `MainActivity` : liste des taches + menu + fragment
- `AddTaskActivity` : formulaire d'ajout
- `DetailTaskActivity` : consultation / modification / suppression
- `InfoFragment` : resume utilisateur / statistiques
- `Task` : modele Realm
- `TaskRepository` : CRUD Realm
- `TaskApplication` : initialisation de Realm

## Ouvrir le projet

1. Decompressez l'archive si necessaire.
2. Ouvrez le dossier `MyTasks` dans Android Studio.
3. Laissez Android Studio faire la synchronisation Gradle.
4. Lancez l'application sur un emulateur ou un telephone.

## Notes utiles

- Le projet utilise **AGP 8.3.1**, **Gradle 8.4** et **Realm 10.19.0**.
- Si Android Studio propose une mise a niveau automatique, vous pouvez d'abord refuser et tester le projet tel quel.
- Le wrapper fourni est minimal. Si Android Studio souhaite regenerer les fichiers de wrapper, vous pouvez accepter.
