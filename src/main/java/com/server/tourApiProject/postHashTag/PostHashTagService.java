package com.server.tourApiProject.postHashTag;

import com.server.tourApiProject.hashTag.HashTag;
import com.server.tourApiProject.hashTag.HashTagRepository;
import com.server.tourApiProject.myHashTag.MyHashTag;
import com.server.tourApiProject.myHashTag.MyHashTagParams;
import com.server.tourApiProject.myHashTag.MyHashTagRepository;
import com.server.tourApiProject.post.Post;
import com.server.tourApiProject.post.PostRepository;
import com.server.tourApiProject.user.User;
import com.server.tourApiProject.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PostHashTagService {
    private final PostHashTagRepository postHashTagRepository;
    private final PostRepository postRepository;
    private final HashTagRepository hashTagRepository;

    public List<PostHashTag> getPostHashTag(Long postId) {
        return postHashTagRepository.findByPostId(postId);
    }

    public void createPostHashTags(List<PostHashTagParams> postHashTagParams) {
        for (PostHashTagParams p : postHashTagParams) {
            PostHashTag postHashTag = new PostHashTag();
            postHashTag.setHashTagName(p.getHashTagName());

            HashTag hashTag = hashTagRepository.findByHashTagName(p.getHashTagName());
            postHashTag.setHashTagId(hashTag.getHashTagId());

            Post post = postRepository.findByUserId(p.getUserId());
            postHashTag.setPost(post);
            postHashTag.setPostId(post.getUserId());

            postHashTagRepository.save(postHashTag);
        }
    }
}
