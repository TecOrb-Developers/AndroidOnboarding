package com.vkpal.androidonboarding

import android.content.Intent
import android.os.Bundle
import com.vkpal.onboardlibrary.OnboardingActivity
import com.vkpal.onboardlibrary.OnboardingCard


class MainActivity : OnboardingActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fancywalkthroughCard1 = OnboardingCard(
            "Chose Pizza",
            "Pizza is a dish of Italian origin, made with an oven-baked, flat, generally round bread that is often covered with tomatoes or a tomato-based sauce and mozzarella cheese. Other toppings are added according to region, culture, or personal preference. ",
            R.drawable.pizza
        )
        val fancywalkthroughCard2 = OnboardingCard(
            "Chose Burger", "A hamburger, or simply burger, is a food consisting of fillings—usually a patty of ground meat, typically beef—placed " +
                    "inside a sliced bun or bread roll.", R.drawable.burger1
        )
        val fancywalkthroughCard3 = OnboardingCard(
            "Chose Momos",
            "Momo is a type of steamed filled dumpling, with origins from Tibet. Momo is native to Tibet, Nepal as well as Indian Himalayan Region of Ladakh, Sikkim, Assam, and Arunachal Pradesh, Himachal Pradesh, Uttarakhand, and Darjeeling",
            R.drawable.momos
        )
        val fancywalkthroughCard4 = OnboardingCard(
            "Chose Spring Roll", "\n" +
                    "A spring roll is a Chinese food consisting of a small roll of thin pastry filled with vegetables and sometimes meat, and then fried.", R.drawable.spring_roll
        )

        fancywalkthroughCard1.backgroundColor = R.color.white
        fancywalkthroughCard1.descriptionTextSize = 12f
        fancywalkthroughCard1.titleTextSize = 18f
        fancywalkthroughCard1.setIconLayoutParams(800, 800, 0, 0, 0, 0)
        fancywalkthroughCard1.setDisplaySkip(true)
        fancywalkthroughCard2.backgroundColor = R.color.white
        fancywalkthroughCard2.descriptionTextSize = 12f
        fancywalkthroughCard2.titleTextSize = 18f
        fancywalkthroughCard2.setIconLayoutParams(800, 800, 0, 0, 0, 0)
        fancywalkthroughCard2.setDisplaySkip(true)
        fancywalkthroughCard3.backgroundColor = R.color.white
        fancywalkthroughCard3.descriptionTextSize = 12f
        fancywalkthroughCard3.titleTextSize = 18f
        fancywalkthroughCard3.setIconLayoutParams(800, 800, 0, 0, 0, 0)
        fancywalkthroughCard3.setDisplaySkip(true)
        fancywalkthroughCard4.backgroundColor = R.color.white
        fancywalkthroughCard4.descriptionTextSize = 12f
        fancywalkthroughCard4.titleTextSize = 18f
        fancywalkthroughCard4.setIconLayoutParams(800, 800, 0, 0, 0, 0)
        fancywalkthroughCard4.setDisplaySkip(true)
        val pages: MutableList<OnboardingCard> = ArrayList<OnboardingCard>()

        pages.add(fancywalkthroughCard1)
        pages.add(fancywalkthroughCard2)
        pages.add(fancywalkthroughCard3)
        pages.add(fancywalkthroughCard4)

        for (page in pages) {
            page.titleColor = R.color.black
            page.descriptionColor = R.color.black
        }
        setFinishButtonTitle("Get Started")
        showNavigationControls(true)
        setColorBackground(R.color.colorGreen)
        //setImageBackground(R.drawable.restaurant);
        //setImageBackground(R.drawable.restaurant);
        setInactiveIndicatorColor(com.vkpal.onboardlibrary.R.color.grey_200)
        setActiveIndicatorColor(R.color.colorGreen)
        setOnboardPages(pages)

    }

    override fun onFinishButtonPressed() {
//        Toast.makeText(this, "Finish Pressed", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, WelcomeActivity::class.java))
    }
}