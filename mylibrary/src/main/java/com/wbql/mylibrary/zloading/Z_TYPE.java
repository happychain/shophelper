package com.wbql.mylibrary.zloading;


import com.wbql.mylibrary.zloading.ball.ElasticBallBuilder;
import com.wbql.mylibrary.zloading.ball.InfectionBallBuilder;
import com.wbql.mylibrary.zloading.ball.IntertwineBuilder;
import com.wbql.mylibrary.zloading.circle.DoubleCircleBuilder;
import com.wbql.mylibrary.zloading.circle.PacManBuilder;
import com.wbql.mylibrary.zloading.circle.RotateCircleBuilder;
import com.wbql.mylibrary.zloading.circle.SingleCircleBuilder;
import com.wbql.mylibrary.zloading.circle.SnakeCircleBuilder;
import com.wbql.mylibrary.zloading.clock.CircleBuilder;
import com.wbql.mylibrary.zloading.clock.ClockBuilder;
import com.wbql.mylibrary.zloading.path.MusicPathBuilder;
import com.wbql.mylibrary.zloading.path.SearchPathBuilder;
import com.wbql.mylibrary.zloading.path.StairsPathBuilder;
import com.wbql.mylibrary.zloading.rect.ChartRectBuilder;
import com.wbql.mylibrary.zloading.rect.StairsRectBuilder;
import com.wbql.mylibrary.zloading.star.LeafBuilder;
import com.wbql.mylibrary.zloading.star.StarBuilder;
import com.wbql.mylibrary.zloading.text.TextBuilder;

/**
 * Created by zyao89 on 2017/3/19.
 * Contact me at 305161066@qq.com or zyao89@gmail.com
 * For more projects: https://github.com/zyao89
 * My Blog: http://zyao89.me
 */
public enum Z_TYPE
{
    CIRCLE(CircleBuilder.class),
    CIRCLE_CLOCK(ClockBuilder.class),
    STAR_LOADING(StarBuilder.class),
    LEAF_ROTATE(LeafBuilder.class),
    DOUBLE_CIRCLE(DoubleCircleBuilder.class),
    PAC_MAN(PacManBuilder.class),
    ELASTIC_BALL(ElasticBallBuilder.class),
    INFECTION_BALL(InfectionBallBuilder.class),
    INTERTWINE(IntertwineBuilder.class),
    TEXT(TextBuilder.class),
    SEARCH_PATH(SearchPathBuilder.class),
    ROTATE_CIRCLE(RotateCircleBuilder.class),
    SINGLE_CIRCLE(SingleCircleBuilder.class),
    SNAKE_CIRCLE(SnakeCircleBuilder.class),
    STAIRS_PATH(StairsPathBuilder.class),
    MUSIC_PATH(MusicPathBuilder.class),
    STAIRS_RECT(StairsRectBuilder.class),
    CHART_RECT(ChartRectBuilder.class),;

    private final Class<?> mBuilderClass;

    Z_TYPE(Class<?> builderClass)
    {
        this.mBuilderClass = builderClass;
    }

    <T extends ZLoadingBuilder> T newInstance()
    {
        try
        {
            return (T) mBuilderClass.newInstance();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
