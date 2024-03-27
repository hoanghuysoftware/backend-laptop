import React, { Component } from "react";
import "./Header.css";
import "../../css/main.css";
// import { Link } from "react-router-dom";

class Header extends Component {
  constructor(props) {
    super(props);
    this.state = {};
  }

  render() {
    return (
      <header>
        <div className="main-wapper">
          <div className="header-top" style={{ backgroundColor: "#ffb700" }}>
            <div className="heder-top-left"></div>
            <div className="heder-top-center">
              <div
                id="carouselExampleControls"
                class="carousel slide header-slide"
                data-ride="carousel"
                style={{ height: "100%" }}
              >
                <div class="carousel-inner" style={{ height: "100%" }}>
                  <div class="carousel-item header-slide-item active">
                    <p className="header-slide-data">
                      Today's Black Friday deals
                      <span style={{ color: "red", fontWeight: "700" }}>
                        {" "}
                        40% off
                      </span>
                    </p>
                  </div>
                  <div class="carousel-item header-slide-item">
                    <p className="header-slide-data">
                      🔥{" "}
                      <span style={{ color: "red", fontWeight: "700" }}>
                        25% OFF
                      </span>{" "}
                      ON YOUR 2 ORDER
                    </p>
                  </div>
                </div>
                <button
                  class="carousel-control-prev"
                  type="button"
                  data-target="#carouselExampleControls"
                  data-slide="prev"
                >
                  <span
                    class="carousel-control-prev-icon"
                    aria-hidden="true"
                  ></span>
                  <span class="sr-only">Previous</span>
                </button>
                <button
                  class="carousel-control-next"
                  type="button"
                  data-target="#carouselExampleControls"
                  data-slide="next"
                >
                  <span
                    class="carousel-control-next-icon"
                    aria-hidden="true"
                  ></span>
                  <span class="sr-only">Next</span>
                </button>
              </div>
            </div>
            <div className="heder-top-right">
              <div className="header-top-contact">
                <div className="header-top-contact__icon">
                  <i class="fa-solid fa-headphones-simple"></i>
                </div>
                <div className="header-top-contact__content">
                  <p>035.286.3062</p>
                </div>
              </div>
            </div>
          </div>
          <div className="header-body">
            <div className="child-wapper header-body-main">
              <div className="header-body-left">
                <div className="header-icon-shop">
                  <img src="./img/logo-favicon.png" alt="shop" />
                </div>
              </div>
              <div className="header-body-center">
                <div className="header-body-search">
                  <div className="header-search-icon">
                    <i class="fa-solid fa-magnifying-glass"></i>
                  </div>
                  <div className="header-search-input">
                    <form action="/" method="post">
                      <input
                        type="text"
                        placeholder="What are you looking for..."
                      />
                    </form>
                  </div>
                </div>
              </div>
              <div className="header-body-right">
                <div className="header-body-right-infor">
                  <div className="header-body-infor-account">
                    <i class="fa-regular fa-user"></i>
                  </div>
                  <div className="header-body-infor-cart">
                    <i class="fa-solid fa-cart-shopping"></i>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div className="header-bottom">
            <div className="child-wapper">
              <ul className="header-link__list">
                <li className="header-link__item">
                  <h2 className="header-link__item-title">
                    <a href="/">Home</a>
                  </h2>
                </li>
                <li className="header-link__item">
                  <h2 className="header-link__item-title">
                    <a href="/">Home</a>
                  </h2>
                </li>
                <li className="header-link__item">
                  <h2 className="header-link__item-title">
                    <a href="/">Home</a>
                  </h2>
                </li>
                <li className="header-link__item">
                  <h2 className="header-link__item-title">
                    <a href="/">Home</a>
                  </h2>
                </li>
                <li className="header-link__item">
                  <h2 className="header-link__item-title">
                    <a href="/">Home</a>
                  </h2>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </header>
    );
  }
}

export default Header;
