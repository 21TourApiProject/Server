package com.server.tourApiProject.bigPost.postHashTag;

import com.server.tourApiProject.hashTag.HashTag;
import com.server.tourApiProject.hashTag.HashTagRepository;
import com.server.tourApiProject.bigPost.post.Post;
import com.server.tourApiProject.bigPost.post.PostRepository;
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

    public Long createPostHashTags(String postContent,List<PostHashTagParams> postHashTagParams) {
        Post post =postRepository.findByPostContent(postContent);
        Long postId =post.getPostId();
        for (PostHashTagParams p : postHashTagParams) {
            PostHashTag postHashTag = new PostHashTag();
            postHashTag.setHashTagName(p.getHashTagName());
            postHashTag.setPost(post);
            postHashTag.setPostId(postId);
            HashTag hashTag = hashTagRepository.findByHashTagName(p.getHashTagName());
            postHashTag.setHashTagId(hashTag.getHashTagId());
            postHashTagRepository.save(postHashTag);
        }
        return postId;
    }
}