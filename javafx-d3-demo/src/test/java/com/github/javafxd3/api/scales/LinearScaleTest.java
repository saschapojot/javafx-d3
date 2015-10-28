package com.github.javafxd3.api.scales;


import org.junit.Test;

import com.github.javafxd3.api.AbstractTestCase;
import com.github.javafxd3.api.D3;
import com.github.javafxd3.api.core.Value;


@SuppressWarnings("javadoc")
public class LinearScaleTest extends AbstractTestCase {

	

	@Override
	@Test
	public void doTest() {
		
		D3 d3 = new D3(webEngine);
		
		LinearScale scale = d3.scale().linear();
		// get default domain
		assertEquals(2, scale.domain().length());
		assertEquals(0, scale.domain().get(0, Value.class).asInt());
		assertEquals(1, scale.domain().get(1, Value.class).asInt());

		// set the domain, keep the default range .get(0,1]
		scale.domain(0, 10);
		assertEquals(2, scale.domain().length());
		assertEquals(0, scale.domain().get(0, Value.class).asInt());
		assertEquals(10, scale.domain().get(1, Value.class).asInt());

		scale.domain("5", "6");
		assertEquals(2, scale.domain().length());
		assertEquals("5", scale.domain().get(0, Value.class).asString());
		assertEquals("6", scale.domain().get(1, Value.class).asString());

		scale.domain(-1, 0, 1).range(
				new String[] { "red", "white", "blue" });
		assertEquals(3, scale.domain().length());
		assertEquals(-1, scale.domain().get(0, Value.class).asInt());
		assertEquals(0, scale.domain().get(1, Value.class).asInt());
		assertEquals(1, scale.domain().get(2, Value.class).asInt());

		// default range
		scale = d3.scale().linear();
		assertEquals(0.0, scale.range().get(0, Double.class));
		assertEquals(1.0, scale.range().get(1, Double.class));

		// set the range
		scale.range(0, 100);
		assertEquals(0.0, scale.range().get(0, Double.class));
		assertEquals(100.0, scale.range().get(1, Double.class));

		scale.range(0, 100, 200);
		assertEquals(0.0, scale.range().get(0, Double.class));
		assertEquals(100.0, scale.range().get(1, Double.class));
		assertEquals(200.0, scale.range().get(2, Double.class));

		scale.range("blah", "bloh", "bluh");
		assertEquals("blah", scale.range().get(0, Double.class));
		assertEquals("bloh", scale.range().get(1, Double.class));
		assertEquals("bluh", scale.range().get(2, Double.class));

		// range round
		scale.rangeRound(0, 100);
		assertEquals(0.0, scale.range().get(0, Double.class));
		assertEquals(100.0, scale.range().get(1, Double.class));

		scale.rangeRound(0, 100, 200);
		assertEquals(0.0, scale.range().get(0, Double.class));
		assertEquals(100.0, scale.range().get(1, Double.class));
		assertEquals(200.0, scale.range().get(2, Double.class));

		scale.rangeRound("blah", "bloh", "bluh");
		assertEquals("blah", scale.range().get(0, Double.class));
		assertEquals("bloh", scale.range().get(1, Double.class));
		assertEquals("bluh", scale.range().get(2, Double.class));

		// clamp
		assertEquals(false, scale.clamp());
		scale.clamp(true);
		assertEquals(true, scale.clamp());

		// ticks
		scale = d3.scale().linear();
		scale.domain(10, 20);
		assertEquals(3, scale.ticks(2).length());
		assertEquals(10.0, scale.ticks(2).get(0, Double.class));
		assertEquals(15.0, scale.ticks(2).get(1, Double.class));
		assertEquals(20.0, scale.ticks(2).get(2, Double.class));

		assertEquals(11, scale.ticks(11).length());
		assertEquals(10.0, scale.ticks(11).get(0, Double.class));
		assertEquals(11.0, scale.ticks(11).get(1,Double.class));
		assertEquals(15.0, scale.ticks(11).get(5, Double.class));
		assertEquals(20.0, scale.ticks(11).get(10, Double.class));

		assertEquals(6, scale.ticks(4).length());
		assertEquals(10.0, scale.ticks(4).get(0, Double.class));
		assertEquals(12.0, scale.ticks(4).get(1, Double.class));
		assertEquals(14.0, scale.ticks(4).get(2, Double.class));
		assertEquals(16.0, scale.ticks(4).get(3, Double.class));
		assertEquals(18.0, scale.ticks(4).get(4, Double.class));
		assertEquals(20.0, scale.ticks(4).get(5, Double.class));

		// tickFormat
		scale = d3.scale().linear();
		scale.domain(10, 20);
		assertEquals("10", scale.tickFormat(2).format(10));
		assertEquals("20", scale.tickFormat(2).format(20));

		assertEquals("10.00", scale.tickFormat(200).format(10));
		assertEquals("20.00", scale.tickFormat(200).format(20));

		assertEquals("015.00", scale.tickFormat(200, "06f").format(15));

		// nice
		scale = d3.scale().linear();
		scale.domain(1.02, 4.98);
		assertEquals(1.02, scale.domain().get(0, Double.class));
		assertEquals(4.98, scale.domain().get(1, Double.class));
		scale.nice();
		assertEquals(1.0, scale.domain().get(0, Double.class));
		assertEquals(5.0, scale.domain().get(1, Double.class));

		// test nice(count) =>
		scale = d3.scale().linear();
		scale.domain(2.1, 2.9);
		scale.nice(6);
		assertEquals(2.1, scale.domain().get(0, Double.class));
		assertEquals(2.9, scale.domain().get(1, Double.class));
		scale = d3.scale().linear();
		scale.domain(2.1, 2.8);
		scale.nice(11);
		assertEquals(2.1d, scale.domain().get(0, Double.class));
		assertEquals(2.8d, scale.domain().get(1, Double.class));

		// apply the function
		scale = d3.scale().linear();
		scale.domain(0, 1).range(0, 10);
		assertEquals(0, scale.apply(0).asInt());
		assertEquals(100, scale.apply(10).asInt());
		assertEquals(1000, scale.apply(100).asInt());
		assertEquals(-100, scale.apply(-10).asInt());

		// invert
		assertEquals(10, scale.invert(100).asInt());
		assertEquals(-10, scale.invert(-100).asInt());

		// copy
		scale.domain(1, 2);
		LinearScale copy = scale.copy();
		copy.domain(2, 3);
		assertEquals(1.0, scale.domain().get(0, Double.class));
		assertEquals(2.0, scale.domain().get(1, Double.class));

		// interpolate
		// scale.interpolate(Interpolators.);
	}
}
