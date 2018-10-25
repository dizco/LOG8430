MiniDraw
========

Purpose
---

**MiniDraw** is a teaching-oriented 2D graphics direct manipulation
framework in Java, inspired by JHotDraw.  It is used in the book
*Flexible, Reliable Software - using Patterns and Agile Development*,
by Henrik Bærbak Christensen, published by CRC Press 2010.

Credits
---

The architecture and some of the code base is from JHotDraw 5.1,
originally developed by Erich Gamma and Thomas Eggenschwiler.

How to Use This Project
---

The current repository contains two subprojects: `minidraw` which is
the core MiniDraw library, and `demo` which contains demonstrations
applications of various features of MiniDraw - manual test cases.

You can find these by issuing `gradle tasks` in the *Testing tasks*
section. To run any of them, just `gradle (name)` and choose the name
of the task.

  *  background: Show MiniDraw's ability to render a background image,
     using the appropriate DrawingView.
  
  *  figures: Show how to add Figures to Drawing manually.

  *  add: Show how to define a custom Tool to add/remove figures to the
     Drawing role.
  
  *  composite: Show how to create CompositeFigures.
  
  *  message: Show how to enable the status line in MiniDraw, and how
     to make a Tool write messages in it.

   * key: Show how to intercept keyboard events in a Tool.
   
   * multiview: Show how to create several DrawingView's associated
     with the Drawing, and show how the Observer pattern ensures they
     show the same graphical contents.
     
   * breakthrough: A more complex demonstration of how the BoardGame
     framework extension in MiniDraw can be injected with delegates
     that couple a simple Breakthrough game to a MiniDraw graphical
     user interface.  


For a description of the architecture and usage of MiniDraw, please
consult the book *Flexible, Reliable Software - using Patterns and
Agile Development*.


How to Use MiniDraw in Your Own Project
---

MiniDraw is published at JCenter (not Maven Central), so
you just have to add a dependency in your `build.gradle` script.

    dependencies {
        compile 'com.baerbak.maven:minidraw:1.11'
    }

If you use Ant as build management tool, you have to update your
`ivysettings.xml` so dependencies are resolved from JCenter instead of
Maven Central, using the \<bintray\> resolver.

