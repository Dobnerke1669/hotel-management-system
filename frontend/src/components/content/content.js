import {React, useState} from 'react';
import OwlCarousel from 'react-owl-carousel';
import 'owl.carousel/dist/assets/owl.carousel.css';
import 'owl.carousel/dist/assets/owl.theme.default.css';
import SingleRoom from '../photos/r1.jpg';
import DoubleRoom from '../photos/r2.jpg';
import TripleRoom from '../photos/r3.jpg';
import A1 from '../photos/a1.jpg';
import A2 from '../photos/a2.jpg';
import Home1 from '../photos/home1.jpg';
import Home2 from '../photos/home2.jpg';
import Home3 from '../photos/home3.jpg';
import Home4 from '../photos/home4.jpg';
import C from '../photos/c.jpg';
import Re from '../photos/re.jpg';
import G1 from '../photos/g1.jpg';
import G2 from '../photos/g2.jpg';
import G3 from '../photos/g3.jpg';
import G4 from '../photos/g4.jpg';
import G5 from '../photos/g5.jpg';
import G6 from '../photos/g6.jpg';
import G7 from '../photos/g7.jpg';
import G8 from '../photos/g8.jpg';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {faPersonSwimming, faSpa, faDumbbell, faSailboat, faWaterLadder, faMicrophone, faWater, faUmbrellaBeach} from "@fortawesome/free-solid-svg-icons";
import './content.css';

const Content = () => {



    const [selected, setSelected] = useState(null)

    const toggle = (i) => {
        if(selected === i){
            return setSelected(null)
        }
        setSelected(i)
    }

    const data = [
        { ///accordionItem open/closed
            title: 'Italian Kitchen', ///accordionIHeading
            description: ///accordionItemContent
                'Lorem ipsum dolor sit amet, consectetur adipisicing elit, set do eiusmod temor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.'
        },
        {
            title: 'Mexican Kitchen',
            description:
                'Lorem ipsum dolor sit amet, consectetur adipisicing elit, set do eiusmod temor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.'
        },
        {
            title: 'Romanian Kitchen',
            description:
                'Lorem ipsum dolor sit amet, consectetur adipisicing elit, set do eiusmod temor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.'
        },
        {
            title: 'German Kitchen',
            description:
                'Lorem ipsum dolor sit amet, consectetur adipisicing elit, set do eiusmod temor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.'

        },
    ]

    return (
        <div>
            <section className="home" id="home">
                <div className="head_container">
                    <div className="box">
                        <div className="text">
                            <h1>Hello.Salut.Hola</h1>
                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor
                                incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud
                                exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. </p>
                            <button>MORE INFO</button>
                        </div>
                    </div>
                    <div className="image">
                        <img src={Home1}  alt="imagine" className="slide" />
                    </div>
                    <div className="image_item">
                        <img src={Home1} alt="" className="slide active" />
                        <img src={Home2} alt="" className="slide" />
                        <img src={Home3} alt="" className="slide" />
                        <img src={Home4} alt="" className="slide" />
                    </div>
                </div>
            </section>
            <section className="about top" id="about">
                <div className="container flex">
                    <div className="left">
                        <div className="img">
                            <img src={A1} alt="" className="image1" />
                            <img src={A2} alt="" className="image2" />
                        </div>
                    </div>
                    <div className="right">
                        <div className="heading">
                            <h5>RAISING COMFOMRT TO THE HIGHEST LEVEL</h5>
                            <h2>Welcome to Luviana Hotel Resort</h2>
                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut
                                labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco
                                laboris nisi ut aliquip ex ea commodo consequat. </p>
                            <p>Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla
                                pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt
                                mollit anim id est laborum.</p>
                            <button className="btn1">READ MORE</button>
                        </div>
                    </div>
                </div>
            </section>
            <section className="wrapper top">
                <div className="container">
                    <div className="text">
                        <h2>Our Amenities</h2>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut
                            labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco
                            laboris nisi ut aliquip ex ea commodo consequat. </p>

                        <div className="content">
                            <div className="box flex">
                                <FontAwesomeIcon icon={faWaterLadder} style={{color: "#ffffff",}} className="icon"/>
                                <span>Swimming pool</span>
                            </div>
                            <div className="box flex">
                                <FontAwesomeIcon icon={faDumbbell} style={{color: "#ffffff",}} className="icon"/>
                                <span>Gym & yogo</span>
                            </div>
                            <div className="box flex">
                                <FontAwesomeIcon icon={faSpa} style={{color: "#ffffff",}} className="icon"/>
                                <span>Spa & massage</span>
                            </div>
                            <div className="box flex">
                                <FontAwesomeIcon icon={faSailboat} style={{color: "#ffffff",}} className="icon"/>
                                <span>Boat Tours</span>
                            </div>
                            <div className="box flex">
                                <FontAwesomeIcon icon={faPersonSwimming} style={{color: "#ffffff",}} className="icon"/>
                                <span>Surfing Lessons</span>
                            </div>
                            <div className="box flex">
                                <FontAwesomeIcon icon={faMicrophone} style={{color: "#ffffff",}} className="icon"/>
                                <span>Conference room</span>
                            </div>
                            <div className="box flex">
                                <FontAwesomeIcon icon={faWater} style={{color: "#ffffff",}} className="icon"/>
                                <span>Diving & smorking</span>
                            </div>
                            <div className="box flex">
                                <FontAwesomeIcon icon={faUmbrellaBeach} style={{color: "#ffffff",}} className="icon"/>
                                <span>Private Beach</span>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <section className="room top" id="room">
                <div className="container">
                    <div className="heading_top flex1">
                        <div className="heading">
                            <h5>RAISING COMFORT TO THE HIGHEST LEVEL</h5>
                            <h2>Rooms $ Suites</h2>
                        </div>
                        <div className="button">
                            <button className="btn1">VIEW ALL</button>
                        </div>
                    </div>

                    <div className="content grid">
                        <div className="box">
                            <div className="img">
                                <img src={SingleRoom} alt="" />
                            </div>
                            <div className="text">
                                <h3>Superior Rooms</h3>
                                <p><span>$</span>101 <span>/per night</span></p>
                            </div>
                        </div>
                        <div className="box">
                            <div className="img">
                                <img src={DoubleRoom} alt="" />
                            </div>
                            <div className="text">
                                <h3>Double Rooms</h3>
                                <p><span>$</span>129 <span>/per night</span></p>
                            </div>
                        </div>
                        <div className="box">
                            <div className="img">
                                <img src={TripleRoom} alt="" />
                            </div>
                            <div className="text">
                                <h3>Premium Soble Rooms</h3>
                                <p><span>$</span>170 <span>/per night</span></p>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <section className="wrapper wrapper2 top">
                <div className="container">
                    <div className="text">
                        <div className="heading">
                            <h5>AT THE HEART OF COMMUNICATION</h5>
                            <h2>People Say</h2>
                        </div>

                        <div className="para">
                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor
                                incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud
                                exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. </p>

                            <div className="box flex">
                                <div className="img">
                                    <img src={C} alt="" />
                                </div>
                                <div className="name">
                                    <h5>KATE PALMER</h5>
                                    <h5>IDAHO</h5>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <section className="restaurant top" id="restaurant">
                <div className="container flex">
                    <div className="left">
                        <img src={Re} alt="" />
                    </div>
                    <div className="right">
                        <div className="text">
                            <h2 className="restaurant-title">Our Restaurant</h2>
                            <p className="restaurant-description"> Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt
                                mollit anim id est laborum.</p>
                        </div>
                        <div className="accordionWrapper">
                            <div className="accordion">
                                {data.map((item, i) =>(
                                    <div className="accordionItem">
                                        <div className="accordionIHeading" onClick={() => toggle(i)}>
                                            <h2>{item.title}</h2>
                                        </div>
                                        <div className={
                                            selected === i ? "accordionItemContent open" : "accordionItemContent close"
                                        }>
                                            {item.description}
                                        </div>
                                    </div>
                                ))}
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <section className="gallery mtop " id="gallery">
                <div className="container">
                    <div className="heading_top flex1">
                        <div className="heading">
                            <h5>WELCOME TO OUR PHOTO GALLERY</h5>
                            <h2>Photo Gallery of Our Hotel</h2>
                        </div>
                        <div className="button">
                            <button className="btn1">VIEW GALLERY</button>
                        </div>
                    </div>
                    <OwlCarousel className='owl-theme' loop margin={10} nav>
                        <div class='item'>
                            <h4><img src={G1} alt='img1'/></h4>
                        </div>
                        <div class='item'>
                            <h4><img src={G2} alt='img2'/></h4>
                        </div>
                        <div class='item'>
                            <h4><img src={G3} alt='img3'/></h4>
                        </div>
                        <div class='item'>
                            <h4><img src={G4} alt='img4'/></h4>
                        </div>
                        <div class='item'>
                            <h4><img src={G5} alt='img5'/></h4>
                        </div>
                        <div class='item'>
                            <h4><img src={G6} alt='img6'/></h4>
                        </div>
                        <div class='item'>
                            <h4><img src={G7} alt='img7'/></h4>
                        </div>
                        <div class='item'>
                            <h4><img src={G8} alt='img8'/></h4>
                        </div>
                    </OwlCarousel>
                </div>
            </section>
        </div>
    );
}


export default Content;