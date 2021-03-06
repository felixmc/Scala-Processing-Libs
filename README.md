# Scala Processing Libs
Scala libraries for the Processing development environment. Contributions to this repository are welcome. Additional documentation can be found on my [blog](http://felixmilea.com/?s=scala+processing).

##Requirements
To use these libraries, you must download Processing and include core.jar and jogl.jar (found in /core/library) to the build path.

##Structure
The libraries come in three primary packages: core, models, and examples. Core contains structures necessary to writing and running Processing sketches. Models contains data structures that aid in writing Processing sketches (colors, interpolation, etc). Examples is simply a collection of sketches that showcase the use of the libraries. All objects within the package are runnable sketches and are documented with comments.

##Getting Started
To write a new Processing sketch using these libraries, simply create an object that extends the `ProcessingSketch` class. The sketch is inherently runnable once the `draw()` method is implemented. The `setup()` method can optionally be overridden as long as `super.setup` is called from the overridden method.  A `config` property of type `SketchConfig` is inherited from `ProcessingSketch` that is used for configuring the running behavior of the sketch.

###Sketch Config
The `ProcessingSketch` class has a `config` property of type `SketchConfig` that is used for configuring the running behavior of the sketch. The `SketchConfig` class has the following public properties:

- `fullScreen`: `Boolean` determines whether the sketch is ran in full screen. __default__: _true_
- `resizeable`: `Boolean` if sketch is not full screen, determines whether the sketch window is resizeable __default__: _false_
- `windowTitle`: `String` if sketch is not full screen, determines the title for the sketch window __default__: _"Processing Sketch"_
- `dimensions`: `java.awt.Dimension` if sketch is not full screen, determines the default dimensions of the sketch window __default__: _800x800_
- `smooth`: `Boolean` determines whether `smooth()` is called in `ProcessingSketch.setup()`. Can be overridden by implementing `setup()` in a sketch. __default__: _true_
- `frameRate`: `Int` determines the default frame rate called in `ProcessingSketch.setup()`. Can be overridden by implementing `setup()` in a sketch. __default__: _60_
