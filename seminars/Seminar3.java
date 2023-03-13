package seminars;

import java.util.*;

public class Seminar3 {
    public static void main(String[] args) {
        /*
        Заполнить список названиями планет Солнечной системы в произвольном порядке с повторениями.
        Вывести название каждой планеты и количество его повторений в списке.
         */

        // Define an array of planet names
        String[] planets = {"Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune", "Pluto"};


        //1
        // Create an empty list to store planet names
//        List<String> planetList = new ArrayList<>();
//
//        // Fill the list with random planet names with repetitions
//        Random random = new Random();
//        for (int i = 0; i < 10; i++) {
//            int index = random.nextInt(planets.length);
//            planetList.add(planets[index]);
//        }
//
//        System.out.println(planetList);
//
//        int[] count = new int[9];
//        for (String name : planetList) {
//            for (int j = 0; j < planets.length; j++) {
//                if (name.equals(planets[j])) {
//                    count[j]++;
//                    break;
//                }
//            }
//        }
//
//        for (int j = 0; j < planets.length; j++) {
//            System.out.println(planets[j] + ":" + count[j]);
//        }

        // 2
        /*
        // Count the repetitions of each planet in the list
        Map<String, Integer> planetCounts = new HashMap<>();
        for (String planet : planetList) {
            Integer count = planetCounts.get(planet);
            if (count == null) {
                planetCounts.put(planet, 1);
            } else {
                planetCounts.put(planet, count + 1);
            }
        }

        // Print the name of each planet and the number of repetitions in the list
        for (String planet : planetCounts.keySet()) {
            int count = planetCounts.get(planet);
            System.out.println(planet + ": " + count);
        }*/

        //3

            String[] solarSystem1 = {"mercury", "venus", "earth", "mars", "jupiter", "saturn", "uranus", "neptune"};
            ArrayList<String> solarSystemRes = new ArrayList<String>();
            Random rnd = new Random();
            for (int i = 0; i < 10; i++) {
                solarSystemRes.add(solarSystem1[rnd.nextInt(7)]);
            }
            System.out.println(solarSystemRes);
            for (int i = 0; i < solarSystem1.length; i++) {
                System.out.printf("%s встречается %d раз \n",solarSystem1[i],Collections.frequency(solarSystemRes, solarSystem1[i]));
            }

    }
}

