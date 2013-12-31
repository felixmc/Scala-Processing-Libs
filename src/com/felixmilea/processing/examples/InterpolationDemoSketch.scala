import com.felixmilea.processing.core.ProcessingSketch
import com.felixmilea.processing.models.Interpolation
import com.felixmilea.processing.models.RGBColor
import com.felixmilea.processing.models.UpdatableManager
import com.felixmilea.processing.models.HSBColor

object InterpolationDemoSketch extends ProcessingSketch {

  // list of easing functions
  val easingFns: List[( Float, Float, Float, Float ) => Float] = List( Interpolation.linear,
    Interpolation.easeInQuad, Interpolation.easeOutQuad, Interpolation.easeInOutQuad,
    Interpolation.easeInCubic, Interpolation.easeOutCubic, Interpolation.easeInOutCubic,
    Interpolation.easeInQuart, Interpolation.easeOutQuart, Interpolation.easeInOutQuart,
    Interpolation.easeInQuint, Interpolation.easeOutQuint, Interpolation.easeInOutQuint,
    Interpolation.easeInSine, Interpolation.easeOutSine, Interpolation.easeInOutSine,
    Interpolation.easeInExpo, Interpolation.easeOutExpo, Interpolation.easeInOutExpo,
    Interpolation.easeInCirc, Interpolation.easeOutCirc, Interpolation.easeInOutCirc,
    Interpolation.easeInBounce, Interpolation.easeOutBounce, Interpolation.easeInOutBounce
  )

  // create list of interpolations based on list of easing functions
  val inters = easingFns.map( e => new Interpolation( start = 0, end = 255, duration = 180, fn = e, direction = Interpolation.Direction.Alternate ) )

  var colWidth = 0

  override def setup() {
    super.setup
    strokeWeight( 1 )
    stroke( 255 )

    colWidth = config.dimensions.width / inters.length

    // add interpolations to update manager
    UpdatableManager ++= inters
  }

  override def draw() {
    background( 0 )

    // draw vertical rectangle for each interpolation
    for ( i <- 0 until inters.length ) {
      // set fill color of rectangle based on interpolation value
      fill( new RGBColor( red = inters( i ).value, green = 80 + ( inters( i ).value / 10 ) ) )
      rect( i * colWidth, -1, colWidth, config.dimensions.height + 1 )
    }

    // update all interpolations
    UpdatableManager.update
  }

}