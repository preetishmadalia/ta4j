/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017-2022 Ta4j Organization & respective
 * authors (see AUTHORS)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package org.ta4j.core.indicators.ichimoku;

import org.ta4j.core.BarSeries;
import org.ta4j.core.indicators.CachedIndicator;
import org.ta4j.core.num.NaN;
import org.ta4j.core.num.Num;

/**
 * Ichimoku clouds: Senkou Span B (Leading Span B) indicator
 *
 * @see <a href=
 *      "http://stockcharts.com/school/doku.php?id=chart_school:technical_indicators:ichimoku_cloud">
 *      http://stockcharts.com/school/doku.php?id=chart_school:technical_indicators:ichimoku_cloud</a>
 */
public class IchimokuSenkouSpanBIndicator extends CachedIndicator<Num> {

    // ichimoku avg line indicator
    private final IchimokuLineIndicator lineIndicator;

    /**
     * Displacement on the chart (usually 26)
     */
    private final int offset;

    /**
     * Constructor.
     * 
     * @param series the series
     */
    public IchimokuSenkouSpanBIndicator(BarSeries series) {

        this(series, 52, 26);
    }

    /**
     * Constructor.
     * 
     * @param series   the series
     * @param barCount the time frame (usually 52)
     */
    public IchimokuSenkouSpanBIndicator(BarSeries series, int barCount) {

        this(series, barCount, 26);
    }

    /**
     * Constructor.
     * 
     * @param series   the series
     * @param barCount the time frame (usually 52)
     * @param offset   displacement on the chart
     */
    public IchimokuSenkouSpanBIndicator(BarSeries series, int barCount, int offset) {

        super(series);
        lineIndicator = new IchimokuLineIndicator(series, barCount);
        this.offset = offset;
    }

    @Override
    protected Num calculate(int index) {
        int spanIndex = index - offset + 1;
        if (spanIndex >= getBarSeries().getBeginIndex()) {
            return lineIndicator.getValue(spanIndex);
        } else {
            return NaN.NaN;
        }
    }
}
