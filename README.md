# 2048
This is a Java implementation of the classic 2048 game I made for my Introduction to Software Development course. The software architecture follows the Model View Controller (MVC) design pattern. The model modules represent the data structures and accessors/mutators of the data which includes [BoardT](src/model/BoardT.java), [ScoreT](src/model/ScoreT.java), and [DirectionT](src/model/DirectionT.java). The controller modules maintain and control the state of the game and the game logic which includes [Controller](src/controller/Controller.java), and [BoardManager](src/controller/BoardManager.java). The view modules work to display the state of the game using a Java Swing graphical user interface (GUI) and translate player interactions which includes [View](src/view/View.java), [ComponentUI](src/view/ComponentUI.java), [ScoreUI](src/view/ScoreUI.java), [TileUI](src/view/TileUI.java), [BoardUI](src/view/BoardUI.java), and [MessageUI](src/view/MessageUI.java).

**Demo**: `make demo`

**Test**: `make test`

**Documentation**: [docs.pdf](docs.pdf)

**Module Interface Specification + Design Critique**: [spec.pdf](spec/spec.pdf)
