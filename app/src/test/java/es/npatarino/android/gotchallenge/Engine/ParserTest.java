package es.npatarino.android.gotchallenge.Engine;

import org.junit.Test;

import java.io.IOException;

import es.npatarino.android.gotchallenge.Models.GoTCharacter;
import es.npatarino.android.gotchallenge.Models.GoTHouse;
import es.npatarino.android.gotchallenge.UtilsTest;

import static com.nitorcreations.Matchers.reflectEquals;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Carlos Carrasco on 15/3/16.
 */
public class ParserTest {

    private String CHARACTER_NAME = "Daenerys Targaryen";
    private String CHARACTER_IMAGE_URL = "https://s3-eu-west-1.amazonaws.com/npatarino/got/c8533a36-5e15-4a57-baed-fc87f7eba578.jpg";
    private String CHARACTER_DESCRIPTION = "Daenerys Targaryen is the daughter of King Aerys II Targaryen (also referred to as The Mad King) and his sister-wife Rhaella, and is one of the last survivors of House Targaryen. She serves as the third-person narrator of thirty-one chapters throughout A Game of Thrones, A Clash of Kings, A Storm of Swords, and A Dance with Dragons. She is the only monarch or claimant of such given point of view chapters in the novels. Thirteen years before the events of the series, she was born in the midst of a storm, earning her the nickname 'Stormborn'. Shortly thereafter, Daenerys and her brother Viserys fled to Braavos; Rhaella had died in childbirth. They spent the following years wandering the Free Cities.";

    private String HOUSE_NAME = "House Lannister";
    private String HOUSE_IMAGE_URL = "https://s3-eu-west-1.amazonaws.com/npatarino/got/lannister.jpg";
    private String HOUSE_ID = "50fab25b";

    public String getCorrectData() throws IOException {
        return new UtilsTest().getStringFromAssets("data");
    }

    public String getErrorData() throws IOException {
        return new UtilsTest().getStringFromAssets("data_error");
    }

    public String getEmptyData() throws IOException {
        return new UtilsTest().getStringFromAssets("data_empty");
    }

    @Test
    public void getAllCharacters() throws IOException {
        Parser parser = new Parser(getCorrectData());
        assertThat(parser.getAllCharacters().size(), is(18));
    }

    @Test
    public void getAllCharactersWithEmptyData() throws IOException {
        Parser parser = new Parser(getEmptyData());
        assertThat(parser.getAllCharacters().size(), is(0));
    }

    @Test(expected = Exception.class)
    public void getAllCharactersWithErrorData() throws IOException {
        Parser parser = new Parser(getErrorData());
        assertThat(parser.getAllCharacters().size(), is(0));
    }

    @Test
    public void getAllHouses() throws IOException {
        Parser parser = new Parser(getCorrectData());
        assertThat(parser.getAllHouses().size(), is(9));
    }

    @Test
    public void getAllHousesWithEmptyData() throws IOException {
        Parser parser = new Parser(getEmptyData());
        assertThat(parser.getAllHouses().size(), is(0));
    }

    @Test(expected = Exception.class)
    public void getAllHousesWithErrorData() throws IOException {
        Parser parser = new Parser(getErrorData());
        assertThat(parser.getAllHouses().size(), is(0));
    }

    @Test
    public void getCharacter() throws IOException {
        GoTCharacter fakeCharacter = new GoTCharacter();
        fakeCharacter.setName(CHARACTER_NAME);
        fakeCharacter.setImageUrl(CHARACTER_IMAGE_URL);
        fakeCharacter.setDescription(CHARACTER_DESCRIPTION);

        Parser parser = new Parser(getCorrectData());
        GoTCharacter realCharacter = parser.getAllCharacters().get(1);
        assertThat(fakeCharacter, reflectEquals(realCharacter));
    }

    @Test
    public void getHouse() throws IOException {
        GoTHouse fakeHouse = new GoTHouse();
        fakeHouse.setName(HOUSE_NAME);
        fakeHouse.setImageUrl(HOUSE_IMAGE_URL);
        fakeHouse.setId(HOUSE_ID);

        Parser parser = new Parser(getCorrectData());
        GoTHouse realHouse = parser.getAllHouses().get(0);
        assertThat(fakeHouse.getName(), is(realHouse.getName()));
        assertThat(fakeHouse.getImageUrl(), is(realHouse.getImageUrl()));
        assertThat(fakeHouse.getId(), is(realHouse.getId()));
    }

}