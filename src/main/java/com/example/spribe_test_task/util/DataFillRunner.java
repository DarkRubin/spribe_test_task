package com.example.spribe_test_task.util;

import com.example.spribe_test_task.entity.Unit;
import com.example.spribe_test_task.repository.UnitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataFillRunner {

    private final static List<Unit> UNITS = List.of(
            new Unit("Luxury penthouse with ocean view", new BigDecimal(548723)),
            new Unit("Cozy cottage in the countryside", new BigDecimal(215674)),
            new Unit("Modern studio in city center", new BigDecimal(398412)),
            new Unit("Spacious villa with private pool", new BigDecimal(782194)),
            new Unit("Stylish loft in downtown", new BigDecimal(362571)),
            new Unit("Charming bungalow near the beach", new BigDecimal(474201)),
            new Unit("Elegant townhouse with garden", new BigDecimal(594837)),
            new Unit("Sunny apartment with balcony", new BigDecimal(308129)),
            new Unit("Rustic cabin in the mountains", new BigDecimal(228765)),
            new Unit("Classic duplex with rooftop terrace", new BigDecimal(689431)),
            new Unit("Seaside condo with panoramic view", new BigDecimal(519642)),
            new Unit("Family-friendly home with backyard", new BigDecimal(344829)),
            new Unit("Minimalist flat with smart home features", new BigDecimal(275193)),
            new Unit("Luxury mansion with wine cellar", new BigDecimal(812305)),
            new Unit("Small studio with modern decor", new BigDecimal(192847)),
            new Unit("Historic house with vintage charm", new BigDecimal(647528)),
            new Unit("Beachfront resort-style apartment", new BigDecimal(723591)),
            new Unit("Ski chalet with fireplace", new BigDecimal(387456)),
            new Unit("Bohemian-style apartment in arts district", new BigDecimal(259364)),
            new Unit("Eco-friendly tiny home", new BigDecimal(178423)),
            new Unit("Elegant suite with jacuzzi", new BigDecimal(456981)),
            new Unit("High-tech smart home", new BigDecimal(531742)),
            new Unit("Sprawling estate with golf course", new BigDecimal(971285)),
            new Unit("Minimalist apartment with open layout", new BigDecimal(293874)),
            new Unit("Riverside cabin with scenic views", new BigDecimal(337921)),
            new Unit("Chic condo near shopping district", new BigDecimal(485273)),
            new Unit("Quaint home with countryside charm", new BigDecimal(209671)),
            new Unit("Futuristic glass house", new BigDecimal(610459)),
            new Unit("Mountain retreat with hot tub", new BigDecimal(394287)),
            new Unit("Traditional house with courtyard", new BigDecimal(562193)),
            new Unit("Urban apartment with rooftop garden", new BigDecimal(452783)),
            new Unit("Charming house with a white picket fence", new BigDecimal(311928)),
            new Unit("Designer loft with industrial aesthetic", new BigDecimal(582634)),
            new Unit("Secluded nature lodge", new BigDecimal(247981)),
            new Unit("Lavish estate with private cinema", new BigDecimal(845291)),
            new Unit("Apartment with stunning skyline view", new BigDecimal(573826)),
            new Unit("Quirky treehouse getaway", new BigDecimal(163482)),
            new Unit("Rustic stone cottage", new BigDecimal(312759)),
            new Unit("Mediterranean-style villa", new BigDecimal(693872)),
            new Unit("Converted warehouse loft", new BigDecimal(471286)),
            new Unit("Modern house with infinity pool", new BigDecimal(792134)),
            new Unit("Boutique hotel-style apartment", new BigDecimal(356741)),
            new Unit("Cozy home with fireplace", new BigDecimal(217638)),
            new Unit("Ultra-modern glass penthouse", new BigDecimal(685142)),
            new Unit("Countryside estate with stables", new BigDecimal(934572)),
            new Unit("Beach shack with hammocks", new BigDecimal(184263)),
            new Unit("High-rise penthouse with concierge", new BigDecimal(651892)),
            new Unit("Minimalist concrete house", new BigDecimal(425781)),
            new Unit("Classic brownstone townhouse", new BigDecimal(739521)),
            new Unit("Lakeside retreat with private dock", new BigDecimal(328579)),
            new Unit("Farmhouse with modern interior", new BigDecimal(593471)),
            new Unit("Converted barn with vaulted ceilings", new BigDecimal(271684)),
            new Unit("Elegant duplex near park", new BigDecimal(437128)),
            new Unit("City apartment with home office", new BigDecimal(314289)),
            new Unit("Historic loft with exposed brick", new BigDecimal(482561)),
            new Unit("Luxury suite with spa access", new BigDecimal(728314)),
            new Unit("Small eco-cabin with solar panels", new BigDecimal(197456)),
            new Unit("Castle-style home with turrets", new BigDecimal(894732)),
            new Unit("Cozy studio with skylights", new BigDecimal(265314)),
            new Unit("Floating house on the river", new BigDecimal(583921)),
            new Unit("Japanese-style zen home", new BigDecimal(429781)),
            new Unit("Townhouse with underground garage", new BigDecimal(672834)),
            new Unit("Luxury houseboat with deck", new BigDecimal(342798)),
            new Unit("Penthouse with private elevator", new BigDecimal(769531)),
            new Unit("Tropical bungalow with outdoor shower", new BigDecimal(395178)),
            new Unit("Industrial-style apartment", new BigDecimal(512493)),
            new Unit("Seaside retreat with lighthouse view", new BigDecimal(278361)),
            new Unit("Cottage with a thatched roof", new BigDecimal(147952)),
            new Unit("Sustainable home with green roof", new BigDecimal(376829)),
            new Unit("Underground bunker home", new BigDecimal(859317)),
            new Unit("Sky villa with helipad", new BigDecimal(915283)),
            new Unit("Artist’s loft with gallery space", new BigDecimal(431792)),
            new Unit("Mansion with ballroom", new BigDecimal(738265)),
            new Unit("Apartment with vintage charm", new BigDecimal(342187)),
            new Unit("Open-concept loft with glass walls", new BigDecimal(521839)),
            new Unit("Treehouse with rope bridge", new BigDecimal(293471)),
            new Unit("Lake cabin with fishing dock", new BigDecimal(467128)),
            new Unit("Ultra-modern villa with home automation", new BigDecimal(781362)),
            new Unit("Apartment with reading nook", new BigDecimal(219734)),
            new Unit("Cozy yurt in the forest", new BigDecimal(158297)),
            new Unit("Luxury cabin with sauna", new BigDecimal(495831)),
            new Unit("Suburban house with large porch", new BigDecimal(623789)),
            new Unit("Floating dome house", new BigDecimal(341782)),
            new Unit("Remote getaway cabin", new BigDecimal(272534)),
            new Unit("Apartment with a hidden library", new BigDecimal(364978)),
            new Unit("Studio apartment with a loft bed", new BigDecimal(187263)),
            new Unit("Elegant villa with fountains", new BigDecimal(582974)),
            new Unit("Rustic retreat with wooden beams", new BigDecimal(328571)),
            new Unit("Geodesic dome home", new BigDecimal(241785)),
            new Unit("Historic mansion with grand staircase", new BigDecimal(769832)),
            new Unit("Beach villa with private boardwalk", new BigDecimal(498271)),
            new Unit("Apartment with custom home theater", new BigDecimal(534197)),
            new Unit("Converted windmill house", new BigDecimal(217489)),
            new Unit("Mountain lodge with panoramic view", new BigDecimal(654382)),
            new Unit("Sky-high condo with smart tech", new BigDecimal(718423)),
            new Unit("Modern house with retractable roof", new BigDecimal(823749)),
            new Unit("Luxury resort-style home", new BigDecimal(938127)),
            new Unit("Hidden gem in tropical paradise", new BigDecimal(547281))
    );

    private final UnitRepository unitRepository;

    @Bean
    public CommandLineRunner fillUnits() {
        return _ -> unitRepository.saveAll(UNITS);
    }

}
