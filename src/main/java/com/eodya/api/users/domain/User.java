package com.eodya.api.users.domain;

import com.eodya.api.bookmark.domain.Bookmark;
import com.eodya.api.common.entity.BaseEntity;
import com.eodya.api.recommendation.domain.Recommendation;
import com.eodya.api.review.domain.Review;
import com.eodya.api.place.domain.Place;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//todo - bookmark, recommend status = true
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotNull
    @Column(name = "nickname")
    private String nickname;

    @NotNull
    @Column(name = "oauth_id")
    private String OAuthId;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "oauth_provider")
    private OAuthProvider OAuthProvider;

    @OneToMany(mappedBy = "user")
    private List<Place> places = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Bookmark> bookmarks = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Recommendation> recommendations = new ArrayList<>();

    @Builder
    private User(
            String nickname,
            String OAuthId,
            OAuthProvider OAuthProvider
    ) {
        this.nickname = nickname;
        this.OAuthId = OAuthId;
        this.OAuthProvider = OAuthProvider;
    }

    public void setUserNickName(String nickname) {
        this.nickname = nickname;
    }
}
