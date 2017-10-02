package assignment1;

import java.util.*;

public class LocationFinder {

    /**
     * @require The set of locations on the postal network, locations, is not
     *          null and each location in the set of locations is not null and
     *          has a unique identifier in the range [0, locations.size()-1].
     * 
     *          The set of locations contains the source and destination
     *          locations, and those two locations are not equal.
     * 
     *          The time after which the package departed the source location,
     *          ts, is non-negative (i.e. 0 <= ts). The time that the package
     *          arrived at the destination location by, td, is later than ts
     *          (i.e. ts < td).
     * 
     *          The deliveries in the deliveries log are sorted in ascending
     *          order by their departure times.
     *
     *          For each delivery x in the deliveries, the source and
     *          destination of x are in the set of locations, and ts <
     *          x.departure() < x.arrival() < td.
     * 
     *          The delay d is a positive integer (i.e. 0 < d).
     * 
     * @ensure Returns the set of locations on the postal network such that the
     *         package may have departed the source location at some time after
     *         ts, and arrived at the destination location at some time before
     *         td, having been routed through the location (using the given
     *         deliveries), with a delay in that location of at least d time
     *         units between two deliveries. (See the assignment handout for
     *         details.)
     */
    public static Set<Location> findLocations(Set<Location> locations,
            Location source, int ts, Location destination, int td,
            List<Delivery> deliveries, int d) {

        return null; // REMOVE THIS LINE AND IMPLEMENT THIS METHOD
    }

}