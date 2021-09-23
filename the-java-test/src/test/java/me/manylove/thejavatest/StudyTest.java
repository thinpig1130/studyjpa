package me.manylove.thejavatest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.*;

import java.time.Duration;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudyTest {

    @Test
    @DisplayName("assertNotNull 사용")
    @Tag("fast")
    @Order(2)
    void study_assertNotNull(){
        System.out.println("============== study_assertNotNull 메서드 실행");
        Study study = new Study();
        assertNotNull(study);
    }

    @Test
    @DisplayName("assertEquals 사용")
    @Tag("slow")
    void study_assertEquals(){
        System.out.println("============== study_assertEquals 메서드 실행");
        Study study = new Study();
        assertEquals(StudyStatus.DRAFT, study.getStatus(), "스터디를 처음 만들면 상태값이 DRAFT 여야 한다.");
        assertEquals(StudyStatus.DRAFT, study.getStatus(), ()-> "스터디를 처음 만들면 상태값이 DRAFT 여야 한다.");

        assertEquals(StudyStatus.DRAFT, study.getStatus(), new Supplier<String>() {
            @Override
            public String get() {
                return "스터디를 처음 만들면 상태값이 DRAFT 여야 한다.";
            }
        });
        // 메시지를 만드는 연산이 복잡한 경우, 람다식으로 만들어주면 연산을 효율적으로 필요한 시점에 한다.
        // 처음의 경우 처럼 연산을 그대로 적을 경우, 테스트 실패 여부와 상관없이 연산이 진행 된다.
        // 따라서, 연산이 복잡한 경우, 성능을 위해서 람다식으로 표현하는것이 바람직하다.

    }

    @Test
    @DisplayName("assertTrue 사용")
    @Tag("fast")
    void study_assertTrue(){
        System.out.println("============== study_assertTrue 메서드 실행");
        Study study = new Study();
        assertTrue(study.getLimit() > 0, "스터디 최대 참석 가능 인원은 0보다 커야 한다.");
    }

    @DisplayName("assertAll 사용")
    @SlowTest
    @Order(5)
    void study_assertAll(){
        System.out.println("============== study_assertAll 메서드 실행");

        /* 성공 */
        Study study = new Study();

        /* 하나가 실패 해도 다른 구문 들이 실행되는지 확인 */
//        Study study = new Study(-10);
//        study.setStatus(StudyStatus.STARTED);
        
        assertAll(
                ()->assertNotNull(study),
                ()->assertTrue(study.getLimit() > 0, "스터디 최대 참석 가능 인원은 0보다 커야 한다."),
                ()->assertEquals(StudyStatus.DRAFT, study.getStatus(), "스터디를 처음 만들면 상태값이 DRAFT 여야 한다.")
        );

        // 특정 구문 테스트가 실패 하더라도 다른 테스트가 실패 했더라도 다른 테스트 구문들을 실행 할 수 있도록 함.
        // 실패한 내용들을 한번에 알 수 있음.
    }

    @Test
    @DisplayName("assertThrows 사용")
    @Tag("fast")
    @Order(4)
    void study_assertThrows(){
        System.out.println("============== study_assertThrows 메서드 실행");

        Study study = new Study();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> study.setLimit(-10));
        String message = exception.getMessage();

        assertEquals("limit은 0보다 커야 한다.", message);
    }

    @FastTest
    @DisplayName("assertTimeout 사용")
    @Order(3)
    void study_assertTimeout(){
        System.out.println("============== study_assertTimeout 메서드 실행");

        assertTimeout(Duration.ofMillis(100), ()->{
            new Study(10);
            /* 테스트 실패를 위한 코드 */
//            Thread.sleep(300);
        });

        // TODO ThreadLocal , assertTimeoutPreemptively 사용시 주의. 예상치 못한 결과를 얻게 될 수 있음.
    }

    @DisplayName("테스트 반복 실행")
    @RepeatedTest(value = 10, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
    void repeatTest(RepetitionInfo repetitionInfo){
        System.out.println("test" + repetitionInfo.getCurrentRepetition() + "/" +
                repetitionInfo.getTotalRepetitions());
    }

    @DisplayName("ValueSource 변수값 바꿔가면서 테스트")
    @ParameterizedTest(name="{index} {displayName} message={0}")
    @ValueSource(ints = {10, 20, 30, 40})
//    @EmptySource
//    @NullSource
    @Order(1)
    void parameterizedTest(@ConvertWith(StudyConverter.class) Study study){
        System.out.println(study);
    }

    static class StudyConverter extends SimpleArgumentConverter {

        @Override
        protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
            assertEquals(Study.class, targetType, "Can only convert to Study");
            return new Study(Integer.parseInt(source.toString()));
        }
    }


    @DisplayName("ValueSource 변수값 바꿔가면서 테스트")
    @ParameterizedTest(name="{index} {displayName} message={0}")
    @CsvSource({"10, '자바 스터디'", "20, 스프링"})
//    @NullAndEmptySource
    void parameterizedTest2(Integer limit, String name){
        Study study = new Study(limit, name);
        System.out.println(study);
    }

    @DisplayName("ValueSource 변수값 바꿔가면서 테스트")
    @ParameterizedTest(name="{index} {displayName} message={0}")
    @CsvSource({"10, '자바 스터디'", "20, 스프링"})
    void parameterizedTest2(@AggregateWith(StudyAggregator.class) Study study){
        System.out.println(study);
    }

    static class StudyAggregator implements ArgumentsAggregator{

        @Override
        public Object aggregateArguments(ArgumentsAccessor argumentsAccessor, ParameterContext parameterContext) throws ArgumentsAggregationException {
            return new Study(argumentsAccessor.getInteger(0), argumentsAccessor.getString(1));
        }
    }


    @Test
    @Disabled
    void anotation_disabled(){
        System.out.println("============== anotation_disabled 메서드 실행");
    }

    @BeforeAll
    static void beforeAll(){
        System.out.println("BeforeAll 실행");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("AfterAll 실행");
    }
    
    @BeforeEach
    void beforeEach(){
        System.out.println("BeforeEach 실행");
    }
    
    @AfterEach
    void afterEach(){
        System.out.println("AfterEach 실행");
    }

}

