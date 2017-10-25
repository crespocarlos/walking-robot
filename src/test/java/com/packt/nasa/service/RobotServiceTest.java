package com.packt.nasa.service;

import com.packt.nasa.domain.Point;
import com.packt.nasa.domain.enums.CardinalPoint;
import com.packt.nasa.domain.enums.Direction;
import com.packt.nasa.service.impl.RobotServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.Stubber;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;
import static org.thymeleaf.util.StringUtils.concat;

@RunWith(MockitoJUnitRunner.class)
public class RobotServiceTest {

    @InjectMocks
    RobotServiceImpl service;

    @Mock
    NavigationService navigationService;

    @Before
    public void before() {
        Stubber voidAnswer = doAnswer(new Answer() {
            @Override
            public Object answer(final InvocationOnMock invocation) throws Throwable {
                final Object[] args = invocation.getArguments();
                final Object mock = invocation.getMock();
                return null;

            }
        });

        voidAnswer.when(this.navigationService).changeCardinalPoint(any(Direction.class));
        voidAnswer.when(this.navigationService).changeLocation();

        when(this.navigationService.getCurrentLocation())
                .thenReturn(new Point(0, 0, CardinalPoint.NORTH));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionNullArgument() {
        service.walk("");
    }

    @Test
    public void shouldStayInTheSamePosition() {
        Point result = service.walk(concat(Direction.RIGHT.toString(), Direction.LEFT.toString()));
        assertEquals(0, result.getX());
        assertEquals(0, result.getY());
        assertEquals(CardinalPoint.NORTH, result.getCardinalPoint());
    }
}
