import React from 'react'
import "./footer.css";

const Footer = () => {

    return (
        <footer className="footer">
            <div className="main-footer">
                <div className="grid-container">

                    <div className="col">
                        <h3>Accepted payment methods</h3>
                        <div className="row">
                            <img src={"https://img.icons8.com/color/48/000000/visa.png"} alt="Visa Card" className="pay_method"/>
                            <img src={"https://img.icons8.com/color/48/000000/mastercard.png"} alt="Master Card" className="pay_method"/>
                            <img src={"https://img.icons8.com/color-glass/48/000000/paypal.png"} alt="Paypal Card" className="pay_method"/>
                            <img src={"https://img.icons8.com/fluency/48/000000/amex.png"} alt="Amex Card" className="pay_method"/>
                        </div>
                    </div>

                    <div className="col">
                            <h3> Contact us</h3>
                            <div className="row">
                                <img src={"https://img.icons8.com/30/ffffff/gmail.png"} alt="Gmail" className="photo"/>
                               <a className="text" href={"mailto:hotel@gmail.com"}>hotel@gmail.com</a>
                            </div>
                            <div className="row">
                                <img src={"https://img.icons8.com/30/ffffff/phone.png"} alt="Phone" className="photo"/>
                               <a className="text" href={"tel:+0267465450"}><i>+40 267 465 450</i></a>
                            </div>
                    </div>

                    <div className="col">
                        <h3>Address</h3>
                            <div className="row">
                                <img src={"https://img.icons8.com/30/ffffff/marker.png"} alt ="Location" className="photo"/>
                                    <a className="text" href="https://www.google.com/maps/place/Fortech/@46.7513837,23.5732169,17z/data=!3m1!4b1!4m6!3m5!1s0x47490dcd88b4ed5b:0x1e9c94ad374e64fb!8m2!3d46.7513801!4d23.5757918!16s%2Fg%2F11rqx54z7?entry=ttu">Str. Frunzișului, Nr.106, Cluj-Napoca</a>
                            </div>
                    </div>

                    <div className="col">
                        <h3>For customers</h3>
                            <a href="/careHelp" className="nav-link">Care & Help</a>
                            <a className="nav-link">Accounts</a>
                            <a href="/terms" className="nav-link">Terms & Conditions</a>
                    </div>

                </div>

                <div className="row">
                    <i>Copyright</i>
                    <img src={"https://img.icons8.com/20/ffffff/copyright.png"} alt="Copyright" className="photo"/>
                    <i>2023 All rights reserved Team3 FORTECH</i>
                </div>
            </div>
        </footer>
    );
}
export default Footer;