package common;

import com.any.common.core.string.Chars;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestChars {

    @Test
    void test(){

        Assertions.assertTrue(Chars.isChineseLetter('中'));
        Assertions.assertTrue(Chars.isEnglishLetter('g'));
        Assertions.assertTrue(Chars.isDigitLetter('8'));
    }

    @Test
    void testTo(){
        final char c = 'ａ';
        Assertions.assertEquals(c, Chars.toFullWidth('a'));
        Assertions.assertEquals('a', Chars.toHalfWidth(c));

        final char d = '，';
        Assertions.assertEquals(d, Chars.toFullWidth(','));
        Assertions.assertEquals(',', Chars.toHalfWidth(d));

        for(int idx = 0x41; idx < 0x5a; ++idx){
            Assertions.assertTrue(Chars.isUpperCaseLetter((char)idx));
        }

        for(int idx = 0x41; idx < 0x5a; ++idx){
            Assertions.assertTrue(Chars.isLowCaseLetter(Chars.toLowCase((char)idx)));
        }
    }
}
