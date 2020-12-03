package com.zemoso.springbootassignment;

import com.zemoso.springbootassignment.dao.RestaurantRepository;
import com.zemoso.springbootassignment.entity.Restaurant;
import com.zemoso.springbootassignment.entity.Review;
import com.zemoso.springbootassignment.service.RestaurantService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RestaurantApplicationTests {

	@Autowired
	private RestaurantService restaurantService;

	@MockBean
    private RestaurantRepository restaurantRepository;

	private List<Restaurant> getSampleRestaurants(){
        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant1 = new Restaurant
                (1,"Sai Ram","Kakinada",
                        "","best for tiffins",5.5);
        Restaurant restaurant2 = new Restaurant
                (2,"Ganesh","Rajmundry",
                        "www.ganesh.com","well known for meals",7.2);
        Restaurant restaurant3 = new Restaurant
                (3,"Parlour","Rajmundry",
                        "www.parlour.com","well known for meals",6.2);
        restaurants.add(restaurant1);
        restaurants.add(restaurant2);
        restaurants.add(restaurant3);
        return restaurants;
    }

	@Test
	void testFindAll() {
        List<Restaurant> restaurants = getSampleRestaurants();
        Mockito.when(restaurantRepository.findAll()).thenReturn(restaurants);
        List<Restaurant> listOfRestaurants = restaurantService.findAll();

        assertEquals(restaurants.size(),listOfRestaurants.size());
	}

	@Test()
    void testFindById(){
	    int theId = 2;
	    List<Restaurant> restaurants = getSampleRestaurants();
	    Mockito.when(restaurantRepository.findById(theId)).thenReturn(Optional.of(restaurants.get(theId)));

	    assertEquals(restaurants.get(theId),restaurantService.findById(theId));

        theId = 1;
        Mockito.when(restaurantRepository.findById(theId)).thenReturn(Optional.of(restaurants.get(theId)));

        assertEquals(restaurants.get(theId),restaurantService.findById(theId));

        theId=4;
        Mockito.when(restaurantRepository.findById(theId)).thenReturn(Optional.empty());
        Restaurant restaurant = restaurantService.findById(theId);

        assertEquals(null,restaurant);
    }

    @Test
    void testSave(){
        Restaurant restaurant4 = new Restaurant
                (4,"Park","Rajmundry",
                        "www.park.com","well known for tiffins",6.7);
        restaurantService.save(restaurant4);

        Mockito.verify(restaurantRepository,Mockito.times(1)).
                save(restaurant4);
    }

    @Test
    void testDeleteById(){
	    int theId=3;
        restaurantService.deleteById(theId);

        Mockito.verify(restaurantRepository,Mockito.times(1)).
                deleteById(theId);
    }

    @Test
    void testAddReview(){
        int theId=1;
        Review review = new Review(1,theId,"sam","excellent");
        Restaurant restaurant = new Restaurant
                (theId,"Sai Ram","Kakinada",
                        "","best for tiffins",5.5);
        Mockito.when(restaurantRepository.findById(theId)).
                thenReturn(Optional.of(restaurant));
        restaurantService.addReview(review);

        Mockito.verify(restaurantRepository,Mockito.times(1)).
                save(restaurant);
    }

}
